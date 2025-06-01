package front.ActionGUI.Sorter;

import back.Control.ToySorter;
import back.Toy.Toy;
import back.Log.LoggerUtility;
import front.ActionGUI.Command;
import front.ActionGUI.SortDialogUI;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SortToysDialog implements Command {
    private final ToySorter sorter;
    private final SortDialogUI dialogUI;
    private Runnable refreshCallback;

    public SortToysDialog(ToySorter sorter, SortDialogUI dialogUI) {
        this.sorter = sorter;
        this.dialogUI = dialogUI;
    }

    public SortToysDialog(List<Toy> toys, SortDialogUI dialogUI) {
        this(new ToySorter(toys), dialogUI);
    }
    public void setRefreshCallback(Runnable callback) {
        this.refreshCallback = callback;
    }

    @Override
    public void execute() {
        LoggerUtility.logInfo("Запуск сортування іграшок (через діалог)");

        Map<String, Integer> options = ToySorter.getOptionsMap();
        List<String> labels = options.keySet().stream().toList();

        Optional<String> result = dialogUI.askSortOption(labels);
        if (result.isEmpty()) return;

        int choice = options.getOrDefault(result.get(), -1);
        if (choice == -1 || !sorter.sortBy(choice)) {
            LoggerUtility.logWarning("✖ Некоректний параметр сортування: " + result.get());
            dialogUI.showError("Невірний вибір параметра.");
        } else {
            LoggerUtility.logInfo("✔ Сортування виконано за параметром: " + result.get());
            if (refreshCallback != null) {
                refreshCallback.run();
            }
        }
    }
}
