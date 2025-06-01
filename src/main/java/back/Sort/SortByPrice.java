package back.Sort;

import back.Log.LoggerUtility;
import back.Toy.Toy;

import java.util.Comparator;
import java.util.List;

public class SortByPrice implements Sorting{
    private final List<Toy> toys;
    public SortByPrice(List<Toy> toys){
        this.toys=toys;
    }

    @Override
    public void sort() {
        LoggerUtility.logInfo("Сортування іграшок за ціною розпочато...");
        toys.sort(Comparator.comparingInt(Toy::getPrice));
        LoggerUtility.logInfo("✔ Іграшки відсортовано за ціною.");
    }
}
