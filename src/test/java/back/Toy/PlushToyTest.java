package back.Toy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlushToyTest {

    @Test
    void testConstructorAndGetters() {
        PlushToy toy = new PlushToy("Ведмедик", "М'яка", 300, 0, "Плюш", 3, true, 20, 15, 10);

        assertEquals("Ведмедик", toy.getName());
        assertEquals("М'яка", toy.getType());
        assertEquals(300, toy.getPrice());
        assertEquals("Плюш", toy.getMaterial());
        assertEquals(3, toy.getAgeRestrictions());
        assertTrue(toy.isPresenceOfSoundEffects());
        assertEquals(20, toy.getHeight());
        assertEquals(15, toy.getLength());
        assertEquals(10, toy.getWidth());
    }

    @Test
    void testCalculateSize() {
        PlushToy toy = new PlushToy("Слоник", "М'яка", 250, 0, "Плюш", 4, false, 10, 10, 10);

        assertEquals(1000, toy.calculateSize()); // 10 * 10 * 10
    }

    @Test
    void testToString() {
        PlushToy toy = new PlushToy("Песик", "М'яка", 200, 0, "Плюш", 2, true, 5, 4, 3);

        String output = toy.toString();
        assertTrue(output.contains("Песик"));
        assertTrue(output.contains("звукові ефекти: true"));
        assertTrue(output.contains("розмір: 60"));
    }
}
