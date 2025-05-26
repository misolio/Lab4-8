package back.Filter;

import back.Toy.Toy;

import java.util.List;
import java.util.stream.Collectors;

public class FilterByAgeRestrictions implements Filtering {
    private final List<Toy> toys;

    public FilterByAgeRestrictions(List<Toy> toys){
        this.toys=toys;
    }

    @Override
    public List<Toy> filter(String value) {
        int ageRestriction = Integer.parseInt(value);
        return toys.stream()
                .filter(toy -> toy.getAgeRestrictions() <= ageRestriction)
                .collect(Collectors.toList());
    }
}
