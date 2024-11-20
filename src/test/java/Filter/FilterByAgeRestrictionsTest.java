package Filter;

import Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterByAgeRestrictionsTest {
    private List<Toy> toys;
    private FilterByAgeRestrictions filterByAgeRestrictions;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        filterByAgeRestrictions = new FilterByAgeRestrictions(toys);
    }

    @Test
    void filter() {
        Toy toy1 = Mockito.mock(Toy.class);
        Toy toy2 = Mockito.mock(Toy.class);
        Toy toy3 = Mockito.mock(Toy.class);

        Mockito.when(toy1.getAgeRestrictions()).thenReturn(3);
        Mockito.when(toy2.getAgeRestrictions()).thenReturn(5);
        Mockito.when(toy3.getAgeRestrictions()).thenReturn(7);

        toys.add(toy1);
        toys.add(toy2);
        toys.add(toy3);

        List<Toy> filteredToys = filterByAgeRestrictions.filter("5");

        assertEquals(2, filteredToys.size());
        assertEquals(3, filteredToys.get(0).getAgeRestrictions());
        assertEquals(5, filteredToys.get(1).getAgeRestrictions());
    }
}
