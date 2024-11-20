package Toy;

public class EducationalToy extends Toy {
    private String typeOfToy;
    private int numberOfDetails;

    public EducationalToy(String name, String type, int price, int size, String material, int ageRestrictions, String gameType,
                          String typeOfToy, int numberOfDetails) {
        super(name, type, price, size, material, ageRestrictions, gameType);
        this.typeOfToy = typeOfToy;
        this.numberOfDetails = numberOfDetails;
    }

    public String getTypeOfToy() {
        return typeOfToy;
    }

    public int getNumberOfDetails() {
        return numberOfDetails;
    }

    @Override
    public int calculateSize() {
        return numberOfDetails * 10;
    }

    @Override
    public String toString() {
        return super.toString() +
                "вид='" + typeOfToy + '\'' +
                ", кількість деталей=" + numberOfDetails +
                ", розмір=" + calculateSize() +
                '}';
    }
}
