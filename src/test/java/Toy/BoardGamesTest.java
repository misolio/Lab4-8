package Toy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardGamesTest {

    @Test
    void calculateSize() {
        BoardGames boardGame = new BoardGames("Chess", "Board Game", 20, 5, "Wood", 6, "Strategy",
                "Classic", 2, 2, 60);
        assertEquals(4, boardGame.calculateSize());
    }

    @Test
    void testToString() {
        BoardGames boardGame = new BoardGames("Chess", "Board Game", 20, 5, "Wood", 6, "Strategy",
                "Classic", 2, 2, 60);
        String expectedString = "'Chess', тип ='Board Game', ціна=20, матеріал='Wood', вікові обмеження=6, тип гри='Strategy'" +
                "вид='Classic', мінімальна кількість гравців=2, максимальна кількість гравців=2, час гри=60, розмір=4}";
        assertEquals(expectedString, boardGame.toString());
    }
}
