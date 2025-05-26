package back.Control;

import back.Toy.Toy;
import back.Filter.*;

import java.util.*;
import java.util.stream.Collectors;

public class ToyFinder {
    private final List<Toy> originalToys;
    private List<Toy> filteredToys;
    private final Map<Integer, Filtering> filters = new HashMap<>();
    public ToyFinder(List<Toy> toys) {
        this.originalToys = toys;
        this.filteredToys = new ArrayList<>(toys);
        filters.put(1, new FilterByName(toys));
        filters.put(2, new FilterByPrice(toys));
        filters.put(3, new FilterByMaterial(toys));
        filters.put(4, new FilterBySize(toys));
        filters.put(5, new FilterByAgeRestrictions(toys));
    }

    public List<Toy> getFilteredToys() {
        return filteredToys;
    }
    public boolean filterBy(int paramChoice, String value) {
        if (!filters.containsKey(paramChoice)) return false;
        Filtering selectedFilter = filters.get(paramChoice);
        filteredToys = selectedFilter.filter(value).stream()
                .filter(filteredToys::contains)
                .collect(Collectors.toList());
        return true;
    }
    public void reset() {
        filteredToys = new ArrayList<>(originalToys);
    }

    public static Map<String, Integer> getOptionsMap() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("1. Назва", 1);
        map.put("2. Ціна (менша введеної)", 2);
        map.put("3. Матеріал", 3);
        map.put("4. Розмір", 4);
        map.put("5. Вікові обмеження (менші введеного)", 5);
        return map;
    }
}
