package Sort;

import Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortByMaterialTest {
    private List<Toy> toys;
    private SortByMaterial sortByMaterial;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        sortByMaterial = new SortByMaterial(toys);
    }

    @Test
    void sort() {
        Toy toy1 = Mockito.mock(Toy.class);
        Toy toy2 = Mockito.mock(Toy.class);
        Toy toy3 = Mockito.mock(Toy.class);

        Mockito.when(toy1.getMaterial()).thenReturn("Wood");
        Mockito.when(toy2.getMaterial()).thenReturn("Plastic");
        Mockito.when(toy3.getMaterial()).thenReturn("Metal");

        toys.add(toy1);
        toys.add(toy2);
        toys.add(toy3);

        sortByMaterial.sort();

        assertEquals("Metal", toys.get(0).getMaterial());
        assertEquals("Plastic", toys.get(1).getMaterial());
        assertEquals("Wood", toys.get(2).getMaterial());
    }
}
