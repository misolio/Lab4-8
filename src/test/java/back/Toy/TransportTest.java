package back.Toy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransportTest {

    @Test
    void testConstructorAndGetters() {
        Transport toy = new Transport("Автобус", "Транспорт", 500, 0, "Пластик", 5,
                "Автобус", 10, 20, 5, 6, true);

        assertEquals("Автобус", toy.getName());
        assertEquals("Транспорт", toy.getType());
        assertEquals(500, toy.getPrice());
        assertEquals("Пластик", toy.getMaterial());
        assertEquals(5, toy.getAgeRestrictions());
        assertEquals("Автобус", toy.getTypeOfTransport());
        assertEquals(10, toy.getHeight());
        assertEquals(20, toy.getLength());
        assertEquals(5, toy.getWidth());
        assertEquals(6, toy.getNumberOfWheels());
        assertTrue(toy.isOnElectricControl());
    }

    @Test
    void testCalculateSize() {
        Transport toy = new Transport("Трактор", "Транспорт", 400, 0, "Метал", 6,
                "Трактор", 2, 4, 5, 4, false);

        assertEquals(40, toy.calculateSize()); // 2 * 4 * 5
    }

    @Test
    void testToString() {
        Transport toy = new Transport("Пожежна машина", "Транспорт", 600, 0, "Пластик", 4,
                "Пожежна", 3, 5, 2, 4, true);

        String output = toy.toString();
        assertTrue(output.contains("Пожежна машина"));
        assertTrue(output.contains("тип машинки: Пожежна"));
        assertTrue(output.contains("електроуправління: true"));
        assertTrue(output.contains("розмір: 30"));
    }

    @Test
    void testCopyConstructor() {
        Transport original = new Transport("Самоскид", "Транспорт", 450, 0, "Пластик", 3,
                "Грузовик", 3, 3, 3, 4, true);
        Transport copy = new Transport(original);

        assertEquals(original.getName(), copy.getName());
        assertEquals(original.getTypeOfTransport(), copy.getTypeOfTransport());
        assertEquals(original.getNumberOfWheels(), copy.getNumberOfWheels());
        assertTrue(copy.isOnElectricControl());
    }
}
