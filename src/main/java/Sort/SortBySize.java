package Sort;

import Toy.Toy;

import java.util.Comparator;
import java.util.List;

public class SortBySize implements Sorting{
    private final List<Toy> toys;
    public SortBySize(List<Toy> toys){
        this.toys=toys;
    }
    @Override
    public void sort() {
        toys.sort(Comparator.comparingInt(Toy::calculateSize));
        System.out.println("Іграшки відсортовано за розміром.");
    }
}
