package back.Control;

import back.Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ToySorterTest {

    // Тестовий клас Toy
    static class TestToy extends Toy {
        public TestToy(String name, String type, int price, int size, String material, int ageRestrictions) {
            super(name, type, price, size, material, ageRestrictions);
        }

        @Override
        public int calculateSize() {
            return size;
        }
    }

    private List<Toy> toys;
    private ToySorter toySorter;
    private Toy toy1, toy2, toy3;

    @BeforeEach
    void setUp() {
        toy1 = new TestToy("Bear", "Plush", 200, 15, "Cotton", 3);
        toy2 = new TestToy("Alphabet", "Educational", 100, 10, "Paper", 5);
        toy3 = new TestToy("Car", "Transport", 300, 5, "Plastic", 2);

        toys = new ArrayList<>(List.of(toy1, toy2, toy3));
        toySorter = new ToySorter(toys);
    }

    @Test
    void testSortByPrice() {
        toySorter.sortBy(1);
        assertEquals(List.of(toy2, toy1, toy3), toys); // 100, 200, 300
    }

    @Test
    void testSortBySize() {
        toySorter.sortBy(2);
        assertEquals(List.of(toy3, toy2, toy1), toys); // 5, 10, 15
    }

    @Test
    void testSortByMaterial() {
        toySorter.sortBy(3);
        assertEquals(List.of(toy1,toy2, toy3), toys); // Cotton, Plastic, Paper (alphabetically)
    }

    @Test
    void testSortByName() {
        toySorter.sortBy(4);
        assertEquals(List.of(toy2, toy1, toy3), toys); // Alphabet, Bear, Car
    }

    @Test
    void testSortByAgeRestrictions() {
        toySorter.sortBy(5);
        assertEquals(List.of(toy3, toy1, toy2), toys); // 2, 3, 5
    }

    @Test
    void testInvalidSortChoice() {
        boolean result = toySorter.sortBy(99);
        assertFalse(result);
        // Список повинен залишитися без змін
        assertEquals(List.of(toy1, toy2, toy3), toys);
    }

    @Test
    void testOptionsMapContainsAllKeys() {
        Map<String, Integer> options = ToySorter.getOptionsMap();
        assertEquals(5, options.size());
        assertTrue(options.containsKey("1. Ціна"));
        assertEquals(1, options.get("1. Ціна"));
    }
}
