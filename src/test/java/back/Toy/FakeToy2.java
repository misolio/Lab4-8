package back.Toy;

public class FakeToy2 extends Toy {
    public FakeToy2(String name, String type, int price, int minAge, String material, int quantity) {
        super(name, type, price, minAge, material, quantity);
    }

    @Override
    public int calculateSize() {
        return 0; // Неважливо для тестів
    }
}

