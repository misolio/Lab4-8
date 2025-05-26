package back.Toy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToyTest {

    // Тестовий підклас Toy для тестування абстрактних методів
    static class DummyToy extends Toy {
        public DummyToy(String name, String type, int price, int size, String material, int ageRestrictions) {
            super(name, type, price, size, material, ageRestrictions);
        }

        public DummyToy(Toy other) {
            super(other);
        }

        @Override
        public int calculateSize() {
            return size * 2; // фіктивна реалізація
        }
    }

    @Test
    void testConstructorAndGetters() {
        DummyToy toy = new DummyToy("Кубик", "Освітня", 100, 3, "Пластик", 2);

        assertEquals("Кубик", toy.getName());
        assertEquals("Освітня", toy.getType());
        assertEquals(100, toy.getPrice());
        assertEquals(3, toy.getSize());
        assertEquals("Пластик", toy.getMaterial());
        assertEquals(2, toy.getAgeRestrictions());
    }

    @Test
    void testCopyConstructor() {
        DummyToy original = new DummyToy("М'яч", "Спорт", 200, 5, "Гума", 4);
        DummyToy copy = new DummyToy(original);

        assertEquals(original.getName(), copy.getName());
        assertEquals(original.getType(), copy.getType());
        assertEquals(original.getPrice(), copy.getPrice());
        assertEquals(original.getSize(), copy.getSize());
        assertEquals(original.getMaterial(), copy.getMaterial());
        assertEquals(original.getAgeRestrictions(), copy.getAgeRestrictions());
    }

    @Test
    void testCalculateSize() {
        DummyToy toy = new DummyToy("Блок", "Розвивальна", 150, 3, "Дерево", 3);
        assertEquals(6, toy.calculateSize()); // 3 * 2
    }

    @Test
    void testToString() {
        DummyToy toy = new DummyToy("Логічна гра", "Інтелект", 300, 4, "Дерево", 5);
        String output = toy.toString();

        assertTrue(output.contains("Логічна гра"));
        assertTrue(output.contains("Інтелект"));
        assertTrue(output.contains("300"));
        assertTrue(output.contains("Дерево"));
        assertTrue(output.contains("5"));
    }
}
