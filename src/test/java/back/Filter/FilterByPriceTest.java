package back.Filter;

import back.Toy.Toy;
import back.Filter.FilterByPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterByPriceTest {
    private List<Toy> toys;
    private FilterByPrice filterByPrice;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        filterByPrice = new FilterByPrice(toys);
    }

    @Test
    void filter() {
        Toy toy1 = Mockito.mock(Toy.class);
        Toy toy2 = Mockito.mock(Toy.class);
        Toy toy3 = Mockito.mock(Toy.class);

        Mockito.when(toy1.getPrice()).thenReturn(100);
        Mockito.when(toy2.getPrice()).thenReturn(200);
        Mockito.when(toy3.getPrice()).thenReturn(150);

        toys.add(toy1);
        toys.add(toy2);
        toys.add(toy3);

        List<Toy> filteredToys = filterByPrice.filter("150");

        assertEquals(2, filteredToys.size());
        assertEquals(100, filteredToys.get(0).getPrice());
        assertEquals(150, filteredToys.get(1).getPrice());
    }
}
