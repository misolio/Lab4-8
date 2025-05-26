package back.Toy;

public class ToyTestImpl extends Toy {
    public ToyTestImpl(String name, int price) {
        super(name, "Test", price, 0, "Plastic", 3);
    }

    @Override
    public int calculateSize() {
        return size;
    }
}
