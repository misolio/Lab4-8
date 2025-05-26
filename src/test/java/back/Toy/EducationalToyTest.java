package back.Toy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EducationalToyTest {

    @Test
    void testConstructorAndGetters() {
        EducationalToy toy = new EducationalToy("Конструктор", "Освітня", 200, 0, "Пластик", 6, 25);

        assertEquals("Конструктор", toy.getName());
        assertEquals("Освітня", toy.getType());
        assertEquals(200, toy.getPrice());
        assertEquals("Пластик", toy.getMaterial());
        assertEquals(6, toy.getAgeRestrictions());
        assertEquals(25, toy.getNumberOfDetails());
    }

    @Test
    void testCalculateSize() {
        EducationalToy toy = new EducationalToy("Пазл", "Освітня", 150, 0, "Картон", 5, 30);

        assertEquals(300, toy.calculateSize()); // 30 * 10
    }

    @Test
    void testToString() {
        EducationalToy toy = new EducationalToy("Пазл", "Освітня", 150, 0, "Картон", 5, 30);

        String output = toy.toString();
        assertTrue(output.contains("Пазл"));
        assertTrue(output.contains("кількість деталей: 30"));
        assertTrue(output.contains("розмір: 300"));
    }
}
