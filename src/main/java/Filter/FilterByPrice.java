package Filter;

import Toy.Toy;
import java.util.List;
import java.util.stream.Collectors;

public class FilterByPrice implements Filtering{
    private List<Toy> toys;
    public FilterByPrice(List<Toy> toys){
        this.toys=toys;
    }
    @Override
    public List<Toy> filter(String value) {
        int price = Integer.parseInt(value);
        return toys.stream()
                .filter(toy -> toy.getPrice() <= price)
                .collect(Collectors.toList());
    }
}
