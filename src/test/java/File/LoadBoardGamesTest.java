package File;

import Toy.BoardGames;
import Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoadBoardGamesTest {
    private List<Toy> toys;
    private LoadBoardGames loadBoardGames;
    private BufferedReader mockBufferedReader;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        loadBoardGames = new LoadBoardGames(toys);
        mockBufferedReader = Mockito.mock(BufferedReader.class);
    }

    @Test
    public void testLoadBoardGames() throws IOException {
        String line1 = "Монополія,Економічна гра,1500,40,пластик,8,стратегія,економічна,2,6,120";
        String line2 = "Шахи,Класична гра,500,20,дерево,10,логічна,настільна,2,2,60";
        String line3 = "Каркассон,Будівельна гра,1200,35,картон,12,стратегія,будівельна,2,5,90";

        when(mockBufferedReader.readLine())
                .thenReturn(line1)
                .thenReturn(line2)
                .thenReturn(line3)
                .thenReturn(null);

        loadBoardGames.load();

        assertEquals(3, toys.size());

        BoardGames game1 = (BoardGames) toys.get(0);
        assertEquals("Монополія", game1.getName());
        assertEquals("Економічна гра", game1.getType());
        assertEquals(1500, game1.getPrice());
        assertEquals(40, game1.getSize());
        assertEquals("пластик", game1.getMaterial());
        assertEquals(8, game1.getAgeRestrictions());
        assertEquals("стратегія", game1.getGameType());
        assertEquals("економічна", game1.getTypeOfGame());
        assertEquals(2, game1.getMinNumberOfPlayers());
        assertEquals(6, game1.getMaxNumberOfPlayers());
        assertEquals(120, game1.getPlayingTime());

        BoardGames game2 = (BoardGames) toys.get(1);
        assertEquals("Шахи", game2.getName());
        assertEquals("Класична гра", game2.getType());
        assertEquals(500, game2.getPrice());
        assertEquals(20, game2.getSize());
        assertEquals("дерево", game2.getMaterial());
        assertEquals(10, game2.getAgeRestrictions());
        assertEquals("логічна", game2.getGameType());
        assertEquals("настільна", game2.getTypeOfGame());
        assertEquals(2, game2.getMinNumberOfPlayers());
        assertEquals(2, game2.getMaxNumberOfPlayers());
        assertEquals(60, game2.getPlayingTime());

        BoardGames game3 = (BoardGames) toys.get(2);
        assertEquals("Каркассон", game3.getName());
        assertEquals("Будівельна гра", game3.getType());
        assertEquals(1200, game3.getPrice());
        assertEquals(35, game3.getSize());
        assertEquals("картон", game3.getMaterial());
        assertEquals(12, game3.getAgeRestrictions());
        assertEquals("стратегія", game3.getGameType());
        assertEquals("будівельна", game3.getTypeOfGame());
        assertEquals(2, game3.getMinNumberOfPlayers());
        assertEquals(5, game3.getMaxNumberOfPlayers());
        assertEquals(90, game3.getPlayingTime());
    }
}
