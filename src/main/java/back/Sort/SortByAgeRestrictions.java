package back.Sort;

import back.Toy.Toy;

import java.util.Comparator;
import java.util.List;

public class SortByAgeRestrictions implements Sorting{
    private final List<Toy> toys;
    public SortByAgeRestrictions(List<Toy> toys){
        this.toys = toys;
    }
    @Override
    public void sort() {
        toys.sort(Comparator.comparingInt(toy -> toy.getAgeRestrictions()));
        System.out.println("Іграшки відсортовано за віковими обмеженнями.");
    }
}
