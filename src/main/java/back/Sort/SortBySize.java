package back.Sort;

import back.Log.LoggerUtility;
import back.Toy.Toy;

import java.util.Comparator;
import java.util.List;

public class SortBySize implements Sorting{
    private final List<Toy> toys;
    public SortBySize(List<Toy> toys){
        this.toys=toys;
    }
    @Override
    public void sort() {
        LoggerUtility.logInfo("Сортування іграшок за розміром розпочато...");
        toys.sort(Comparator.comparingInt(Toy::calculateSize));
        LoggerUtility.logInfo("✔ Іграшки відсортовано за розміром.");
    }
}
