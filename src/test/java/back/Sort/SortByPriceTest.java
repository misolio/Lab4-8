package back.Sort;

import back.Toy.Toy;
import back.Sort.SortByPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortByPriceTest {
    private List<Toy> toys;
    private SortByPrice sortByPrice;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        sortByPrice = new SortByPrice(toys);
    }

    @Test
    void sort() {
        Toy toy1 = Mockito.mock(Toy.class);
        Toy toy2 = Mockito.mock(Toy.class);
        Toy toy3 = Mockito.mock(Toy.class);

        Mockito.when(toy1.getPrice()).thenReturn(50);
        Mockito.when(toy2.getPrice()).thenReturn(20);
        Mockito.when(toy3.getPrice()).thenReturn(100);

        toys.add(toy1);
        toys.add(toy2);
        toys.add(toy3);

        sortByPrice.sort();

        assertEquals(20, toys.get(0).getPrice());
        assertEquals(50, toys.get(1).getPrice());
        assertEquals(100, toys.get(2).getPrice());
    }
}
