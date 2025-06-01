package back.Sort;

import back.Log.LoggerUtility;
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
        LoggerUtility.logInfo("Сортування іграшок за матеріалом розпочато...");
        toys.sort(Comparator.comparing(Toy::getMaterial));
        LoggerUtility.logInfo("✔ Іграшки відсортовано за матеріалом.");
    }
}
