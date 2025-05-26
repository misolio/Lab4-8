package back.Sort;

import back.Toy.Toy;

import java.util.Comparator;
import java.util.List;

public class SortByMaterial implements Sorting{
    private final List<Toy> toys;
    public SortByMaterial(List<Toy> toys){
        this.toys=toys;
    }
    @Override
    public void sort() {
        toys.sort(Comparator.comparing(Toy::getMaterial));
        System.out.println("Іграшки відсортовано за матеріалом.");
    }
}
