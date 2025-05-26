package back.Filter;

import back.Toy.Toy;
import back.Filter.FilterByName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterByNameTest {
    private List<Toy> toys;
    private FilterByName filterByName;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        filterByName = new FilterByName(toys);
    }

    @Test
    void filter() {
        Toy toy1 = Mockito.mock(Toy.class);
        Toy toy2 = Mockito.mock(Toy.class);
        Toy toy3 = Mockito.mock(Toy.class);

        Mockito.when(toy1.getName()).thenReturn("Barbie");
        Mockito.when(toy2.getName()).thenReturn("Ken");
        Mockito.when(toy3.getName()).thenReturn("Barbie");

        toys.add(toy1);
        toys.add(toy2);
        toys.add(toy3);

        List<Toy> filteredToys = filterByName.filter("Barbie");

        assertEquals(2, filteredToys.size());
        assertEquals("Barbie", filteredToys.get(0).getName());
        assertEquals("Barbie", filteredToys.get(1).getName());
    }
}
