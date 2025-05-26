package back.Toy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardGamesTest {

    @Test
    void testConstructorAndGetters() {
        BoardGames game = new BoardGames(
                "Монополія", "Настільна гра", 350, 20, "Картон", 8,
                2, 6, 60
        );

        assertEquals("Монополія", game.getName());
        assertEquals("Настільна гра", game.getType());
        assertEquals(350, game.getPrice());
        assertEquals(20, game.getSize());
        assertEquals("Картон", game.getMaterial());
        assertEquals(8, game.getAgeRestrictions());
        assertEquals(2, game.getMinNumberOfPlayers());
        assertEquals(6, game.getMaxNumberOfPlayers());
        assertEquals(60, game.getPlayingTime());
    }

    @Test
    void testCalculateSize() {
        BoardGames game = new BoardGames("Гра", "Тип", 100, 0, "Матеріал", 5,
                3, 5, 40);
        assertEquals(8, game.calculateSize()); // 3 + 5
    }

    @Test
    void testToStringContainsExpectedContent() {
        BoardGames game = new BoardGames("UNO", "Карткова", 100, 10, "Картон", 6,
                2, 10, 15);

        String output = game.toString();
        assertTrue(output.contains("UNO"));
        assertTrue(output.contains("кількість гравців: 2 - 10"));
        assertTrue(output.contains("час гри:15"));
        assertTrue(output.contains("розмір:12"));
    }
}
