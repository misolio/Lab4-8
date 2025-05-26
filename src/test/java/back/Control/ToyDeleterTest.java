package back.Control;

import back.Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToyDeleterTest {

    private List<Toy> toyList;
    private ToyDeleter deleter;

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

    @BeforeEach
    void setUp() {
        toyList = new ArrayList<>();
        toy1 = new TestToy("Test 1", "Type1", 100, 5, "Plastic", 3);
        toy2 = new TestToy("Test 2", "Type2", 150, 6, "Wood", 4);
        toyList.add(toy1);
        toyList.add(toy2);

        deleter = new ToyDeleter(toyList);
    }

    @Test
    void testContains_True() {
        assertTrue(deleter.contains(toy1));
    }

    @Test
    void testContains_False() {
        Toy toy3 = new TestToy("Ghost Toy", "Invisible", 999, 10, "Air", 0);
        assertFalse(deleter.contains(toy3));
    }

    @Test
    void testDelete_Success() {
        boolean result = deleter.delete(toy1);
        assertTrue(result);
        assertFalse(toyList.contains(toy1));
    }

    @Test
    void testDelete_Failure() {
        Toy toy3 = new TestToy("Unknown", "None", 0, 1, "Unknown", 1);
        boolean result = deleter.delete(toy3);
        assertFalse(result);
        assertEquals(2, toyList.size()); // нічого не видалилось
    }
}
