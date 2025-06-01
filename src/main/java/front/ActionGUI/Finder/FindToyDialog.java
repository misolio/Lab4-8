package front.ActionGUI.Finder;

import back.Control.ToyFinder;
import back.Toy.Toy;
import back.Log.LoggerUtility;
import front.ActionGUI.Command;

import java.util.*;
import java.util.function.Consumer;

public class FindToyDialog implements Command {
    final ToyFinder finder;
    final Consumer<List<Toy>> displayCallback;
    final ToySearchDialogUI dialogUI;

    public FindToyDialog(List<Toy> toys, Consumer<List<Toy>> displayCallback, ToySearchDialogUI dialogUI) {
        this.finder = new ToyFinder(toys);
        this.displayCallback = displayCallback;
        this.dialogUI = dialogUI;
        LoggerUtility.logInfo("FindToyDialog ініціалізовано.");
    }
    public FindToyDialog(ToyFinder finder, Consumer<List<Toy>> displayCallback, ToySearchDialogUI dialogUI) {
        this.finder = finder;
        this.displayCallback = displayCallback;
        this.dialogUI = dialogUI;
    }

    @Override
    public void execute() {
        LoggerUtility.logInfo("Розпочато пошук іграшок (через діалог).");

        try {
            Optional<Integer> numOpt = dialogUI.askNumberOfParams();
            if (numOpt.isEmpty() || numOpt.get() == null) return;

            int numOfParams = numOpt.get();
            if (numOfParams < 1 || numOfParams > 3) {
                dialogUI.showError("Кількість параметрів повинна бути від 1 до 3.");
                return;
            }

            finder.reset();
            Map<String, Integer> options = ToyFinder.getOptionsMap();
            List<String> available = new ArrayList<>(options.keySet());

            for (int i = 0; i < numOfParams; i++) {
                Optional<String> paramNameOpt = dialogUI.chooseParamName(available, i);
                if (paramNameOpt.isEmpty()) continue;

                String paramName = paramNameOpt.get();
                int paramCode = options.get(paramName);

                Optional<String> valueOpt = dialogUI.askParamValue(paramName);
                if (valueOpt.isEmpty()) continue;

                if (!finder.filterBy(paramCode, valueOpt.get())) {
                    dialogUI.showError("Невдалось застосувати фільтр для параметра \"" + paramName + "\".");
                }
            }

            displayCallback.accept(finder.getFilteredToys());

        } catch (Exception e) {
            LoggerUtility.logError("Помилка під час пошуку іграшок.", e);
            dialogUI.showError("Сталася помилка під час пошуку.");
        }
    }
}
