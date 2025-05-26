package back.Control;

import back.Toy.Toy;

import java.util.List;

public class ToyDeleter {
    private final List<Toy> toys;
    public ToyDeleter(List<Toy> toys) {
        this.toys = toys;
    }
    public boolean contains(Toy toy) {
        return toys.contains(toy);
    }
    public boolean delete(Toy toy) {
        return toys.remove(toy);
    }
}
