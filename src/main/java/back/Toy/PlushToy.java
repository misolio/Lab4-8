package back.Toy;

public class PlushToy extends Toy {
    private boolean presenceOfSoundEffects;
    private int height;
    private int length;
    private int width;

    public PlushToy(String name, String type, int price, int size, String material, int ageRestrictions, boolean presenceOfSoundEffects, int height, int length, int width) {
        super(name, type, price, size, material, ageRestrictions);
        this.presenceOfSoundEffects = presenceOfSoundEffects;
        this.height = height;
        this.length = length;
        this.width = width;
    }


    public boolean isPresenceOfSoundEffects() {
        return presenceOfSoundEffects;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public int calculateSize() {
        return height * length * width;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", \nзвукові ефекти: " + presenceOfSoundEffects +
                ", \nрозмір: " + calculateSize();
    }
}
