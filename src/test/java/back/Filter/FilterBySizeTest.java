package back.Filter;

import back.Toy.Toy;
import back.Filter.FilterBySize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterBySizeTest {
    private List<Toy> toys;
    private FilterBySize filterBySize;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        filterBySize = new FilterBySize(toys);
    }

    @Test
    void filter() {
        Toy toy1 = Mockito.mock(Toy.class);
        Toy toy2 = Mockito.mock(Toy.class);
        Toy toy3 = Mockito.mock(Toy.class);

        Mockito.when(toy1.calculateSize()).thenReturn(500);
        Mockito.when(toy2.calculateSize()).thenReturn(200);
        Mockito.when(toy3.calculateSize()).thenReturn(300);

        toys.add(toy1);
        toys.add(toy2);
        toys.add(toy3);

        List<Toy> filteredToys = filterBySize.filter("300");

        assertEquals(1, filteredToys.size());
        assertEquals(300, filteredToys.get(0).calculateSize());
    }
}
