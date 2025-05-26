package back.Control;

import back.Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ToyFinderTest {

    static class TestToy extends Toy {
        public TestToy(String name, String type, int price, int size, String material, int ageRestrictions) {
            super(name, type, price, size, material, ageRestrictions);
        }

        @Override
        public int calculateSize() {
            return size;
        }
    }

    private Toy toy1;
    private Toy toy2;
    private Toy toy3;
    private List<Toy> toys;
    private ToyFinder finder;

    @BeforeEach
    void setUp() {
        toy1 = new TestToy("Car", "Transport", 300, 10, "Plastic", 5);
        toy2 = new TestToy("Bear", "Plush", 150, 20, "Fabric", 3);
        toy3 = new TestToy("Car", "Transport", 250, 8, "Plastic", 4);

        toys = new ArrayList<>(List.of(toy1, toy2, toy3));
        finder = new ToyFinder(toys);
    }

    @Test
    void testInitialFilteredList_EqualsOriginal() {
        List<Toy> filtered = finder.getFilteredToys();
        assertEquals(3, filtered.size());
        assertTrue(filtered.contains(toy1));
        assertTrue(filtered.contains(toy2));
        assertTrue(filtered.contains(toy3));
    }

    @Test
    void testFilterByName() {
        boolean result = finder.filterBy(1, "Car");
        assertTrue(result);

        List<Toy> filtered = finder.getFilteredToys();
        assertEquals(2, filtered.size());
        assertTrue(filtered.contains(toy1));
        assertTrue(filtered.contains(toy3));
    }

    @Test
    void testFilterByMaterial() {
        boolean result = finder.filterBy(3, "Fabric");
        assertTrue(result);

        List<Toy> filtered = finder.getFilteredToys();
        assertEquals(1, filtered.size());
        assertEquals("Bear", filtered.get(0).getName());
    }

    @Test
    void testMultipleFiltersCombined() {
        finder.filterBy(1, "Car"); // name: Car
        finder.filterBy(2, "280"); // price < 280

        List<Toy> filtered = finder.getFilteredToys();
        assertEquals(1, filtered.size());
        assertEquals(toy3, filtered.get(0));
    }

    @Test
    void testInvalidFilterChoice() {
        boolean result = finder.filterBy(99, "Unknown");
        assertFalse(result);
        assertEquals(3, finder.getFilteredToys().size()); // no change
    }

    @Test
    void testReset() {
        finder.filterBy(1, "Car");
        assertEquals(2, finder.getFilteredToys().size());

        finder.reset();
        assertEquals(3, finder.getFilteredToys().size());
    }

    @Test
    void testOptionsMapContainsExpectedValues() {
        Map<String, Integer> map = ToyFinder.getOptionsMap();
        assertEquals(5, map.size());
        assertTrue(map.containsKey("1. Назва"));
        assertEquals(1, map.get("1. Назва"));
    }
}
