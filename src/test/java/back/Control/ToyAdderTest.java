package back.Control;

import back.Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToyAdderTest {

    private List<Toy> toyList;
    private ToyAdder toyAdder;

    // Власний тестовий клас для тестів
    static class TestToy extends Toy {
        public TestToy(String name, String type, int price, int size, String material, int ageRestrictions) {
            super(name, type, price, size, material, ageRestrictions);
        }

        @Override
        public int calculateSize() {
            return size; // проста реалізація для тесту
        }
    }

    @BeforeEach
    void setUp() {
        toyList = new ArrayList<>();
        toyAdder = new ToyAdder(toyList, 1000); // Бюджет: 1000
    }

    @Test
    void testCanAddToy_WhenAffordable() {
        Toy toy = new TestToy("Test Toy", "Test", 200, 5, "Wood", 3);
        assertTrue(toyAdder.canAddToy(toy));
    }

    @Test
    void testCanAddToy_WhenTooExpensive() {
        Toy toy = new TestToy("Expensive Toy", "Luxury", 1500, 10, "Gold", 8);
        assertFalse(toyAdder.canAddToy(toy));
    }

    @Test
    void testAddToyIfAffordable_Success() {
        Toy toy = new TestToy("Affordable Toy", "Basic", 300, 5, "Plastic", 5);
        boolean result = toyAdder.addToyIfAffordable(toy);

        assertTrue(result);
        assertEquals(1, toyList.size());
        assertEquals(700, toyAdder.getBudget());
    }

    @Test
    void testAddToyIfAffordable_Failure() {
        Toy toy = new TestToy("Too Expensive", "Luxury", 1500, 8, "Metal", 10);
        boolean result = toyAdder.addToyIfAffordable(toy);

        assertFalse(result);
        assertEquals(0, toyList.size());
        assertEquals(1000, toyAdder.getBudget());
    }

    @Test
    void testGetBudget() {
        assertEquals(1000, toyAdder.getBudget());
    }
}
