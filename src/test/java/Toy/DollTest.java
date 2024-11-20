package Toy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DollTest {
    private Doll doll;

    @BeforeEach
    void setUp() {
        // Ініціалізація об'єкта Doll з тестовими даними
        doll = new Doll(
                "Barbie", "Fashion Doll", 150, 0, "Plastic", 6, "Indoor",
                "Classic", true, 30, 10, 5, "Pink", "Blonde"
        );
    }

    @Test
    void testCalculateSize() {
        // Перевірка правильності розрахунку розміру
        int expectedSize = 30 * 10 * 5; // height * length * width
        assertEquals(expectedSize, doll.calculateSize(), "Розмір ляльки обчислено неправильно.");
    }

    @Test
    void testToString() {
        // Перевірка методу toString()
        String expectedString = "'Barbie', тип ='Fashion Doll', ціна=150, матеріал='Plastic', вікові обмеження=6, тип гри='Indoor'" +
                "вид ляльки='Classic', стать=Female, колір одягу='Pink', колір волосся='Blonde', розмір=1500}";
        assertEquals(expectedString, doll.toString(), "Метод toString() повертає некоректний результат.");
    }

    @Test
    void testGetTypeOfDoll() {
        // Перевірка типу ляльки
        assertEquals("Classic", doll.getTypeOfDoll(), "Некоректний тип ляльки.");
    }

    @Test
    void testSex() {
        // Перевірка статі ляльки
        assertTrue(doll.isSex(), "Некоректна стать ляльки: очікувалося Female.");
    }

    @Test
    void testHeightLengthWidth() {
        // Перевірка габаритів ляльки
        assertEquals(30, doll.getHeight(), "Некоректна висота ляльки.");
        assertEquals(10, doll.getLength(), "Некоректна довжина ляльки.");
        assertEquals(5, doll.getWidth(), "Некоректна ширина ляльки.");
    }

    @Test
    void testClothesColor() {
        // Перевірка кольору одягу
        assertEquals("Pink", doll.getClothesColor(), "Некоректний колір одягу ляльки.");
    }

    @Test
    void testHairColor() {
        // Перевірка кольору волосся
        assertEquals("Blonde", doll.getHairColor(), "Некоректний колір волосся ляльки.");
    }
}
