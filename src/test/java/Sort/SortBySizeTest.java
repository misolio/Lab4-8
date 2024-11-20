package Sort;

import Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortBySizeTest {
    private List<Toy> toys;
    private SortBySize sortBySize;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        sortBySize = new SortBySize(toys);
    }

    @Test
    void sort() {
        Toy toy1 = Mockito.mock(Toy.class);
        Toy toy2 = Mockito.mock(Toy.class);
        Toy toy3 = Mockito.mock(Toy.class);

        Mockito.when(toy1.calculateSize()).thenReturn(20);
        Mockito.when(toy2.calculateSize()).thenReturn(10);
        Mockito.when(toy3.calculateSize()).thenReturn(30);

        toys.add(toy1);
        toys.add(toy2);
        toys.add(toy3);

        sortBySize.sort();

        assertEquals(10, toys.get(0).calculateSize());
        assertEquals(20, toys.get(1).calculateSize());
        assertEquals(30, toys.get(2).calculateSize());
    }
}
