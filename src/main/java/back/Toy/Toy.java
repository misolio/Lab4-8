package back.Toy;

public abstract class Toy {
    protected String name;
    protected String type;
    protected int price;
    protected int size;
    protected String material;
    protected int ageRestrictions;

    public Toy(String name, String type, int price, int size, String material, int ageRestrictions) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.size = size;
        this.material = material;
        this.ageRestrictions = ageRestrictions;
    }

    public Toy(Toy other) {
        this.name = other.name;
        this.type = other.type;
        this.price = other.price;
        this.material = other.material;
        this.size = other.size;
        this.ageRestrictions = other.ageRestrictions;
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


    public abstract int calculateSize();

    @Override
    public String toString() {
        return "'" + name + '\'' +
                ",\n тип: '" + type + '\'' +
                ",\n ціна:" + price +
                ",\n матеріал:'" + material + '\'' +
                ",\n вікові обмеження:" + ageRestrictions;
    }
}
