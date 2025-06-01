package back.Control;

import back.Toy.Toy;
import back.Sort.*;
import back.Log.LoggerUtility;

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

        LoggerUtility.logInfo("ToySorter ініціалізовано. Доступні методи сортування: " + sortMap.keySet());
    }

    public boolean sortBy(int choice) {
        Sorting sorter = sortMap.get(choice);
        if (sorter != null) {
            LoggerUtility.logInfo("Сортування за вибором: " + choice + " (" + getKeyByValue(choice) + ")");
            sorter.sort();
            LoggerUtility.logDebug("Сортування завершено. Кількість іграшок: " + toys.size());
            return true;
        } else {
            LoggerUtility.logWarning("Спроба сортування за недопустимим параметром: " + choice);
            return false;
        }
    }

    public static Map<String, Integer> getOptionsMap() {
        Map<String, Integer> options = new LinkedHashMap<>();
        options.put("1. Ціна", 1);
        options.put("2. Розмір", 2);
        options.put("3. Матеріал", 3);
        options.put("4. Ім'я", 4);
        options.put("5. Вікові обмеження", 5);
        return options;
    }

    private String getKeyByValue(int value) {
        return getOptionsMap().entrySet().stream()
                .filter(entry -> entry.getValue() == value)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("Невідомо");
    }
}
