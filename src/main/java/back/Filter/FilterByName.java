package back.Filter;

import back.Toy.Toy;

import java.util.List;
import java.util.stream.Collectors;

public class FilterByName implements Filtering {
    private final List<Toy> toys;
    public FilterByName(List<Toy> toys){
        this.toys=toys;
    }
    @Override
    public List<Toy> filter(String value) {
        return toys.stream()
                .filter(toy -> toy.getName().equalsIgnoreCase(value))
                .collect(Collectors.toList());
    }
}
