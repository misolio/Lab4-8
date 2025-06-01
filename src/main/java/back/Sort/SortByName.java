package back.Sort;

import back.Log.LoggerUtility;
import back.Toy.Toy;

import java.util.Comparator;
import java.util.List;

public class SortByName implements Sorting{
    private final List<Toy> toys;
    public SortByName(List<Toy> toys){
        this.toys=toys;
    }
    @Override
    public void sort() {
        LoggerUtility.logInfo("Сортування іграшок за ім'ям розпочато...");
        toys.sort(Comparator.comparing(Toy::getName));
        LoggerUtility.logInfo("✔ Іграшки відсортовано за ім'ям.");
    }
}
