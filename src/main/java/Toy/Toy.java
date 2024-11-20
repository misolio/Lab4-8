package Toy;


public abstract class Toy {
    protected String name;
    protected String type;
    protected int price;
    protected int size;
    protected String material;
    protected int ageRestrictions;
    protected String gameType;

    public Toy(String name, String type, int price, int size, String material, int ageRestrictions, String gameType) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.size = size;
        this.material = material;
        this.ageRestrictions = ageRestrictions;
        this.gameType = gameType;
    }

    public Toy(Toy other) {
        this.name = other.name;
        this.type = other.type;
        this.price = other.price;
        this.material = other.material;
        this.size = other.size;
        this.ageRestrictions = other.ageRestrictions;
        this.gameType = other.gameType;
    }

    // Гетери для кожного приватного поля
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public String getMaterial() {
        return material;
    }

    public int getAgeRestrictions() {
        return ageRestrictions;
    }

    public String getGameType() {
        return gameType;
    }

    public abstract int calculateSize();

    @Override
    public String toString() {
        return "'" + name + '\'' +
                ", тип ='" + type + '\'' +
                ", ціна=" + price +
                ", матеріал='" + material + '\'' +
                ", вікові обмеження=" + ageRestrictions +
                ", тип гри='" + gameType + '\'' ;
    }
}
