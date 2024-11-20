package Toy;

public class Transport extends Toy {
    private String typeOfTransport;
    private int height;
    private int length;
    private int width;
    private int numberOfWheels;
    private boolean onElectricControl;

    public Transport(String name, String type, int price, int size, String material, int ageRestrictions, String gameType,
                     String typeOfTransport, int height, int length, int width, int numberOfWheels, boolean onElectricControl) {
        super(name, type, price, size, material, ageRestrictions, gameType);
        this.typeOfTransport = typeOfTransport;
        this.height = height;
        this.length = length;
        this.width = width;
        this.numberOfWheels = numberOfWheels;
        this.onElectricControl = onElectricControl;
    }

    public Transport(Transport other) {
        super(other);
        this.typeOfTransport = other.typeOfTransport;
        this.height = other.height;
        this.length = other.length;
        this.width = other.width;
        this.numberOfWheels = other.numberOfWheels;
        this.onElectricControl = other.onElectricControl;
    }

    // Гетери для всіх приватних полів
    public String getTypeOfTransport() {
        return typeOfTransport;
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

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public boolean isOnElectricControl() {
        return onElectricControl;
    }

    @Override
    public int calculateSize() {
        return length * width * height;
    }

    @Override
    public String toString() {
        return super.toString() +
                "тип машинки='" + typeOfTransport + '\'' +
                ", кількість колес=" + numberOfWheels +
                ", електроуправління=" + onElectricControl +
                ", розмір=" + calculateSize() +
                '}';
    }
}
