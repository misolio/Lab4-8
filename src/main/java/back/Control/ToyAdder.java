package back.Control;

import back.Toy.Toy;

import java.util.List;

public class ToyAdder {
    private final List<Toy> toys;
    private int budget;

    public ToyAdder(List<Toy> toys, int budget) {
        this.toys = toys;
        this.budget = budget;
    }

    public boolean canAddToy(Toy toy) {
        return toy.getPrice() <= budget;
    }

    public boolean addToyIfAffordable(Toy toy) {
        if (canAddToy(toy)) {
            toys.add(toy);
            budget -= toy.getPrice();
            return true;
        }
        return false;
    }

    public int getBudget() {
        return budget;
    }
}
