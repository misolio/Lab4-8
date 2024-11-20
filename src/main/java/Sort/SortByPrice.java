package Sort;

import Toy.Toy;

import java.util.Comparator;
import java.util.List;

public class SortByPrice implements Sorting{
    private final List<Toy> toys;
    public SortByPrice(List<Toy> toys){
        this.toys=toys;
    }

    @Override
    public void sort() {
        toys.sort(Comparator.comparingInt(Toy::getPrice));
        System.out.println("Іграшки відсортовано за ціною.");
    }
}
