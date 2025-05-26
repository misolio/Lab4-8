package back.Control;

import back.Toy.Toy;
import back.Sort.*;

import java.util.*;

public class ToySorter {
    private final Map<Integer, Sorting> sortMap = new HashMap<>();
    private final List<Toy> toys;

    public ToySorter(List<Toy> toys) {
        this.toys = toys;
        sortMap.put(1, new SortByPrice(toys));
        sortMap.put(2, new SortBySize(toys));
        sortMap.put(3, new SortByMaterial(toys));
        sortMap.put(4, new SortByName(toys));
        sortMap.put(5, new SortByAgeRestrictions(toys));
    }

    public boolean sortBy(int choice) {
        Sorting sorter = sortMap.get(choice);
        if (sorter != null) {
            sorter.sort();
            return true;
        }
        return false;
    }

    public static Map<String, Integer> getOptionsMap() {
        return Map.of(
                "1. Ціна", 1,
                "2. Розмір", 2,
                "3. Матеріал", 3,
                "4. Ім'я", 4,
                "5. Вікові обмеження", 5
        );
    }
}
