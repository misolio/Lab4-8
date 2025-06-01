package back.Sort;

import back.Log.LoggerUtility;
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
        LoggerUtility.logInfo("Сортування іграшок за віковими обмеженнями розпочато...");
        toys.sort(Comparator.comparingInt(toy -> toy.getAgeRestrictions()));
        LoggerUtility.logInfo("✔ Іграшки відсортовано за віковими обмеженнями.");
    }
}
