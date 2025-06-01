package back.Control;

import back.Toy.Toy;
import back.Log.LoggerUtility;

import java.util.List;

public class ToyAdder {
    private final List<Toy> toys;
    private int budget;

    public ToyAdder(List<Toy> toys, int budget) {
        this.toys = toys;
        this.budget = budget;
        LoggerUtility.logInfo("ToyAdder створено з бюджетом: " + budget);
    }

    public boolean canAddToy(Toy toy) {
        boolean result = toy.getPrice() <= budget;
        LoggerUtility.logDebug("Перевірка можливості додати іграшку \"" + toy.getName() +
                "\" з ціною " + toy.getPrice() + ": " + result);
        return result;
    }

    public boolean addToyIfAffordable(Toy toy) {
        if (canAddToy(toy)) {
            toys.add(toy);
            budget -= toy.getPrice();
            LoggerUtility.logInfo("✔ Іграшку \"" + toy.getName() + "\" додано. Залишок бюджету: " + budget);
            return true;
        } else {
            LoggerUtility.logWarning("✖ Недостатньо бюджету для іграшки \"" + toy.getName() +
                    "\" (ціна: " + toy.getPrice() + ", залишок: " + budget + ")");
            return false;
        }
    }

    public int getBudget() {
        LoggerUtility.logDebug("Отримано поточний бюджет: " + budget);
        return budget;
    }
}
