package back.Filter;

import back.Toy.Toy;

import java.util.List;
import java.util.stream.Collectors;

public class FilterBySize implements Filtering {
    private List<Toy> toys;
    public FilterBySize(List<Toy> toys){
        this.toys=toys;
    }
    @Override
    public List<Toy> filter(String value) {
        int size = Integer.parseInt(value);
        return toys.stream()
                .filter(toy -> toy.calculateSize() == size)
                .collect(Collectors.toList());
    }
}
