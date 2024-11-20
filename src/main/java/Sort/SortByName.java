package Sort;

import Toy.Toy;

import java.util.Comparator;
import java.util.List;

public class SortByName implements Sorting{
    private final List<Toy> toys;
    public SortByName(List<Toy> toys){
        this.toys=toys;
    }
    @Override
    public void sort() {
        toys.sort(Comparator.comparing(Toy::getName));
        System.out.println("Іграшки відсортовано за ім'ям.");
    }
}
