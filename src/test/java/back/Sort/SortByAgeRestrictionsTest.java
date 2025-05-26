package back.Sort;

import back.Toy.Toy;
import back.Sort.SortByAgeRestrictions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortByAgeRestrictionsTest {
    private List<Toy> toys;
    private SortByAgeRestrictions sortByAgeRestrictions;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        sortByAgeRestrictions = new SortByAgeRestrictions(toys);
    }

    @Test
    void sort() {
        Toy toy1 = Mockito.mock(Toy.class);
        Toy toy2 = Mockito.mock(Toy.class);
        Toy toy3 = Mockito.mock(Toy.class);

        Mockito.when(toy1.getAgeRestrictions()).thenReturn(5);
        Mockito.when(toy2.getAgeRestrictions()).thenReturn(3);
        Mockito.when(toy3.getAgeRestrictions()).thenReturn(7);

        toys.add(toy1);
        toys.add(toy2);
        toys.add(toy3);

        sortByAgeRestrictions.sort();

        assertEquals(3, toys.get(0).getAgeRestrictions());
        assertEquals(5, toys.get(1).getAgeRestrictions());
        assertEquals(7, toys.get(2).getAgeRestrictions());
    }
}
