package back.Toy;

public class EducationalToy extends Toy {
    private int numberOfDetails;

    public EducationalToy(String name, String type, int price, int size, String material, int ageRestrictions,  int numberOfDetails) {
        super(name, type, price, size, material, ageRestrictions);
        this.numberOfDetails = numberOfDetails;
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
                ", \nкількість деталей: " + numberOfDetails +
                ", \nрозмір: " + calculateSize() +
                '}';
    }
}
