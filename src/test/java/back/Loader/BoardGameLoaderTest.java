package back.Loader;

import back.Toy.BoardGames;
import back.Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardGameLoaderTest {

    private Connection conn;
    private PreparedStatement toyStmt;
    private PreparedStatement boardGameStmt;
    private ResultSet toyRs;
    private ResultSet boardGameRs;
    private BoardGameLoader loader;

    @BeforeEach
    void setUp() throws Exception {
        conn = mock(Connection.class);
        toyStmt = mock(PreparedStatement.class);
        boardGameStmt = mock(PreparedStatement.class);
        toyRs = mock(ResultSet.class);
        boardGameRs = mock(ResultSet.class);

        loader = new BoardGameLoader(conn);
    }

    @Test
    void testGetToyById_WhenToyExists() throws Exception {
        // Arrange
        when(conn.prepareStatement("SELECT * FROM toy WHERE toy_id = ?")).thenReturn(toyStmt);
        when(toyStmt.executeQuery()).thenReturn(toyRs);
        when(toyRs.next()).thenReturn(true);

        when(conn.prepareStatement("SELECT * FROM board_game WHERE board_game_id = ?")).thenReturn(boardGameStmt);
        when(boardGameStmt.executeQuery()).thenReturn(boardGameRs);
        when(boardGameRs.next()).thenReturn(true);

        when(toyRs.getString("name")).thenReturn("Monopoly");
        when(toyRs.getString("type")).thenReturn("Board Game");
        when(toyRs.getInt("price")).thenReturn(300);
        when(toyRs.getInt("size")).thenReturn(15);
        when(toyRs.getString("material")).thenReturn("Cardboard");
        when(toyRs.getInt("age_restrictions")).thenReturn(7);

        when(boardGameRs.getInt("min_number_of_players")).thenReturn(2);
        when(boardGameRs.getInt("max_number_of_players")).thenReturn(6);
        when(boardGameRs.getInt("playing_time")).thenReturn(60);

        // Act
        Toy toy = loader.getToyById(1);

        // Assert
        assertNotNull(toy);
        assertInstanceOf(BoardGames.class, toy);
        assertEquals("Monopoly", toy.getName());
        assertEquals(300, toy.getPrice());
    }

    @Test
    void testGetToyById_WhenToyDoesNotExist() throws Exception {
        when(conn.prepareStatement("SELECT * FROM toy WHERE toy_id = ?")).thenReturn(toyStmt);
        when(toyStmt.executeQuery()).thenReturn(toyRs);
        when(toyRs.next()).thenReturn(false); // No toy found

        Toy toy = loader.getToyById(999);
        assertNull(toy);
    }

    @Test
    void testLoadAll_WhenDataExists() throws Exception {
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false); // Only one record

        when(rs.getString("name")).thenReturn("UNO");
        when(rs.getString("type")).thenReturn("Card Game");
        when(rs.getInt("price")).thenReturn(120);
        when(rs.getInt("size")).thenReturn(5);
        when(rs.getString("material")).thenReturn("Paper");
        when(rs.getInt("age_restrictions")).thenReturn(6);
        when(rs.getInt("min_number_of_players")).thenReturn(2);
        when(rs.getInt("max_number_of_players")).thenReturn(10);
        when(rs.getInt("playing_time")).thenReturn(30);

        List<Toy> toys = loader.loadAll();

        assertEquals(1, toys.size());
        assertEquals("UNO", toys.get(0).getName());
    }

    @Test
    void testLoadAll_WhenNoData() throws Exception {
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false); // No data

        List<Toy> toys = loader.loadAll();

        assertNotNull(toys);
        assertTrue(toys.isEmpty());
    }
}
