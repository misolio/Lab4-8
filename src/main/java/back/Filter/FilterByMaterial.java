package back.Filter;

import back.Toy.Toy;

import java.util.List;
import java.util.stream.Collectors;

public class FilterByMaterial implements Filtering {
    private List<Toy> toys;
    public FilterByMaterial(List<Toy> toys){
        this.toys=toys;
    }
    @Override
    public List<Toy> filter(String value) {
        return toys.stream()
                .filter(toy -> toy.getMaterial().equalsIgnoreCase(value))
                .collect(Collectors.toList());
    }
}
