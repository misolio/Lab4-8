package back.Toy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DollTest {

    @Test
    void testConstructorAndGetters() {
        Doll doll = new Doll("Барбі", "Лялька", 150, 10, "Пластик", 5,
                true, 30, 5, 10, "Рожевий", "Блондинка");

        assertEquals("Барбі", doll.getName());
        assertEquals("Лялька", doll.getType());
        assertEquals(150, doll.getPrice());
        assertEquals("Пластик", doll.getMaterial());
        assertEquals(5, doll.getAgeRestrictions());
        assertTrue(doll.isSex()); // female
        assertEquals(30, doll.getHeight());
        assertEquals(5, doll.getLength());
        assertEquals(10, doll.getWidth());
        assertEquals("Рожевий", doll.getClothesColor());
        assertEquals("Блондинка", doll.getHairColor());
    }

    @Test
    void testCalculateSize() {
        Doll doll = new Doll("Кен", "Лялька", 120, 0, "Пластик", 5,
                false, 20, 4, 5, "Синій", "Коричневий");

        assertEquals(400, doll.calculateSize()); // 20*4*5
    }

    @Test
    void testToString() {
        Doll doll = new Doll("Кен", "Лялька", 120, 0, "Пластик", 5,
                false, 20, 4, 5, "Синій", "Коричневий");

        String str = doll.toString();
        assertTrue(str.contains("Кен"));
        assertTrue(str.contains("стать: Male"));
        assertTrue(str.contains("колір одягу: Синій"));
        assertTrue(str.contains("колір волосся: Коричневий"));
        assertTrue(str.contains("розмір: 400"));
    }
}
