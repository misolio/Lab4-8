package front.ActionGUI;

import back.Control.ToyFinder;
import back.Toy.Toy;

import java.util.LinkedList;
import java.util.*;
import java.util.Queue;
import java.util.function.Consumer;

class TestableFindToyDialog extends FindToyDialog {
    private final Queue<String> inputQueue = new LinkedList<>();
    private final Queue<String> choiceQueue = new LinkedList<>();
    private List<List<Toy>> results = new ArrayList<>();

    public TestableFindToyDialog(List<Toy> toys) {
        super(toys, createResultCollector());

        // ініціалізуємо посилання на загальний список результатів
        this.results = resultCollector;
    }

    private static List<List<Toy>> resultCollector = new ArrayList<>();

    private static Consumer<List<Toy>> createResultCollector() {
        resultCollector = new ArrayList<>();
        return filtered -> resultCollector.add(filtered);
    }

    public void enqueueInput(String value) {
        inputQueue.add(value);
    }

    public void enqueueChoice(String choice) {
        choiceQueue.add(choice);
    }

    @Override
    public void execute() {
        try {
            int numOfParams = Integer.parseInt(inputQueue.poll());
            if (numOfParams < 1 || numOfParams > 3) return;

            super.finder.reset();
            Map<String, Integer> options = ToyFinder.getOptionsMap();

            for (int i = 0; i < numOfParams; i++) {
                String choice = choiceQueue.poll();
                int paramChoice = options.getOrDefault(choice, -1);
                if (paramChoice == -1) continue;

                String value = inputQueue.poll();
                super.finder.filterBy(paramChoice, value);
            }

            super.displayCallback.accept(super.finder.getFilteredToys());

        } catch (Exception e) {
            // Пропускаємо
        }
    }

    public List<List<Toy>> getResults() {
        return results;
    }
}
