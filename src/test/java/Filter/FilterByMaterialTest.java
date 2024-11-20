package Filter;

import Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterByMaterialTest {
    private List<Toy> toys;
    private FilterByMaterial filterByMaterial;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        filterByMaterial = new FilterByMaterial(toys);
    }

    @Test
    void filter() {
        Toy toy1 = Mockito.mock(Toy.class);
        Toy toy2 = Mockito.mock(Toy.class);
        Toy toy3 = Mockito.mock(Toy.class);

        Mockito.when(toy1.getMaterial()).thenReturn("Plastic");
        Mockito.when(toy2.getMaterial()).thenReturn("Wood");
        Mockito.when(toy3.getMaterial()).thenReturn("Plastic");

        toys.add(toy1);
        toys.add(toy2);
        toys.add(toy3);

        List<Toy> filteredToys = filterByMaterial.filter("Plastic");

        assertEquals(2, filteredToys.size());
        assertEquals("Plastic", filteredToys.get(0).getMaterial());
        assertEquals("Plastic", filteredToys.get(1).getMaterial());
    }
}
