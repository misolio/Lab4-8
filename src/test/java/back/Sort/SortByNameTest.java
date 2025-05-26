package back.Sort;

import back.Toy.Toy;
import back.Sort.SortByName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortByNameTest {
    private List<Toy> toys;
    private SortByName sortByName;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        sortByName = new SortByName(toys);
    }

    @Test
    void sort() {
        Toy toy1 = Mockito.mock(Toy.class);
        Toy toy2 = Mockito.mock(Toy.class);
        Toy toy3 = Mockito.mock(Toy.class);

        Mockito.when(toy1.getName()).thenReturn("Zebra");
        Mockito.when(toy2.getName()).thenReturn("Apple");
        Mockito.when(toy3.getName()).thenReturn("Monkey");

        toys.add(toy1);
        toys.add(toy2);
        toys.add(toy3);

        sortByName.sort();

        assertEquals("Apple", toys.get(0).getName());
        assertEquals("Monkey", toys.get(1).getName());
        assertEquals("Zebra", toys.get(2).getName());
    }
}
