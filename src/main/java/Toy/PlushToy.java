package Toy;

public class PlushToy extends Toy {
    private String typeOfToy;
    private boolean presenceOfSoundEffects;
    private int height;
    private int length;
    private int width;

    public PlushToy(String name, String type, int price, int size, String material, int ageRestrictions, String gameType,
                    String typeOfToy, boolean presenceOfSoundEffects, int height, int length, int width) {
        super(name, type, price, size, material, ageRestrictions, gameType);
        this.typeOfToy = typeOfToy;
        this.presenceOfSoundEffects = presenceOfSoundEffects;
        this.height = height;
        this.length = length;
        this.width = width;
    }

    public String getTypeOfToy() {
        return typeOfToy;
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
                "вид='" + typeOfToy + '\'' +
                ", звукові ефекти=" + presenceOfSoundEffects +
                ", розмір=" + calculateSize() +
                '}';
    }
}
