package Toy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransportTest {
    private Transport transport;

    @BeforeEach
    void setUp() {
        // Ініціалізація об'єкта Transport з тестовими даними
        transport = new Transport(
                "RC Car", "Vehicle", 300, 0, "Plastic", 8, "Outdoor",
                "Car", 10, 20, 15, 4, true
        );
    }

    @Test
    void calculateSize() {
        // Перевірка розрахунку розміру
        int expectedSize = 10 * 20 * 15; // height * length * width
        assertEquals(expectedSize, transport.calculateSize(), "Розмір транспорту обчислено неправильно.");
    }

    @Test
    void testToString() {
        // Перевірка методу toString()
        String expectedString = "'RC Car', тип ='Vehicle', ціна=300, матеріал='Plastic', вікові обмеження=8, тип гри='Outdoor'" +
                "тип машинки='Car', кількість колес=4, електроуправління=true, розмір=3000}";
        assertEquals(expectedString, transport.toString(), "Метод toString() повертає некоректний результат.");
    }

    @Test
    void testCopyConstructor() {
        Transport copy = new Transport(transport);
        assertNotSame(transport, copy, "Конструктор копіювання має створювати новий об'єкт.");
        assertEquals(transport.toString(), copy.toString(), "Копія має мати ті ж самі значення.");
    }

    @Test
    void testTypeOfTransport() {

        assertEquals("Car", transport.getTypeOfTransport(), "Некоректний тип транспорту.");
    }

    @Test
    void testNumberOfWheels() {
        // Перевірка кількості коліс
        assertEquals(4, transport.getNumberOfWheels(), "Некоректна кількість коліс.");
    }

    @Test
    void testOnElectricControl() {

        assertTrue(transport.isOnElectricControl(), "Транспорт повинен бути на електроуправлінні.");
    }

    @Test
    void testHeightLengthWidth() {
        // Перевірка розмірів (висота, довжина, ширина)
        assertEquals(10, transport.getHeight(), "Некоректна висота.");
        assertEquals(20, transport.getLength(), "Некоректна довжина.");
        assertEquals(15, transport.getWidth(), "Некоректна ширина.");
    }
}
