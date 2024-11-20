package Toy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToyTest {

    @Test
    void getName() {
        Toy toy = new BoardGames("Chess", "BoardGame", 50, 10, "Wood", 6, "Strategy", "None", 2, 4, 60);
        Assertions.assertEquals("Chess", toy.getName());
    }

    @Test
    void getAgeRestrictions() {
        Toy toy = new BoardGames("Chess", "BoardGame", 50, 10, "Wood", 6, "Strategy", "None", 2, 4, 60);
        Assertions.assertEquals(6, toy.getAgeRestrictions());
    }

    @Test
    void getPrice() {
        Toy toy = new BoardGames("Chess", "BoardGame", 50, 10, "Wood", 6, "Strategy", "None", 2, 4, 60);
        Assertions.assertEquals(50, toy.getPrice());
    }

    @Test
    void getMaterial() {
        Toy toy = new BoardGames("Chess", "BoardGame", 50, 10, "Wood", 6, "Strategy", "None", 2, 4, 60);
        Assertions.assertEquals("Wood", toy.getMaterial());
    }

    @Test
    void calculateSize() {
        Toy toy = new BoardGames("Chess", "BoardGame", 50, 10, "Wood", 6, "Strategy", "None", 2, 4, 60);
        Assertions.assertEquals(6, toy.calculateSize()); // Передбачається, що метод повертає `size`
    }

    @Test
    void testToString() {
        Toy toy = new BoardGames("Chess", "BoardGame", 50, 10, "Wood", 6, "Strategy", "None", 2, 4, 60);
        String expected = "'Chess', тип ='BoardGame', ціна=50, матеріал='Wood', вікові обмеження=6, тип гри='Strategy'";
        Assertions.assertEquals(true, toy.toString().contains(expected));
    }
}
