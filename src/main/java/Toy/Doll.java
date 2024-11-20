package Toy;

public class Doll extends Toy {
    private String typeOfDoll;
    private boolean sex; // true = female, false = male
    private int height;
    private int length;
    private int width;
    private String clothesColor;
    private String hairColor;

    public Doll(String name, String type, int price, int size, String material, int ageRestrictions, String gameType,
                String typeOfDoll, boolean sex, int height, int length, int width, String clothesColor, String hairColor) {
        super(name, type, price, size, material, ageRestrictions, gameType);
        this.typeOfDoll = typeOfDoll;
        this.sex = sex;
        this.height = height;
        this.length = length;
        this.width = width;
        this.clothesColor = clothesColor;
        this.hairColor = hairColor;
    }

    public String getTypeOfDoll() {
        return typeOfDoll;
    }

    public boolean isSex() {
        return sex;
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

    public String getClothesColor() {
        return clothesColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    @Override
    public int calculateSize() {
        return height * length * width;
    }

    @Override
    public String toString() {
        return super.toString() +
                "вид ляльки='" + typeOfDoll + '\'' +
                ", стать=" + (sex ? "Female" : "Male") +
                ", колір одягу='" + clothesColor + '\'' +
                ", колір волосся='" + hairColor + '\'' +
                ", розмір=" + calculateSize() +
                '}';
    }
}
