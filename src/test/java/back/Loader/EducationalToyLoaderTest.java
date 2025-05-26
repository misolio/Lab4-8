package back.Loader;

import back.Toy.EducationalToy;
import back.Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EducationalToyLoaderTest {

    private Connection conn;
    private EducationalToyLoader loader;
    private PreparedStatement mockStmt;
    private ResultSet mockToyRs;
    private ResultSet mockEduRs;

    @BeforeEach
    void setUp() throws SQLException {
        conn = mock(Connection.class);
        loader = new EducationalToyLoader(conn);
        mockStmt = mock(PreparedStatement.class);
        mockToyRs = mock(ResultSet.class);
        mockEduRs = mock(ResultSet.class);
    }

    @Test
    void testGetToyByIdReturnsToy() throws Exception {
        when(conn.prepareStatement(anyString())).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockToyRs).thenReturn(mockEduRs);

        when(mockToyRs.next()).thenReturn(true);
        when(mockToyRs.getString("name")).thenReturn("Abacus");
        when(mockToyRs.getString("type")).thenReturn("educational");
        when(mockToyRs.getInt("price")).thenReturn(100);
        when(mockToyRs.getInt("size")).thenReturn(30);
        when(mockToyRs.getString("material")).thenReturn("wood");
        when(mockToyRs.getInt("age_restrictions")).thenReturn(3);

        when(mockEduRs.next()).thenReturn(true);
        when(mockEduRs.getInt("number_of_details")).thenReturn(50);

        Toy toy = loader.getToyById(1);

        assertNotNull(toy);
        assertTrue(toy instanceof EducationalToy);
        assertEquals("Abacus", toy.getName());
        assertEquals(100, toy.getPrice());
    }

    @Test
    void testLoadReturnsEmptyListWhenNoToyFound() throws Exception {
        when(conn.prepareStatement(anyString())).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockToyRs);
        when(mockToyRs.next()).thenReturn(false);

        List<Toy> toys = loader.load(1);
        assertTrue(toys.isEmpty());
    }

    @Test
    void testLoadAllReturnsListOfToys() throws Exception {
        when(conn.prepareStatement(anyString())).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockEduRs);

        when(mockEduRs.next()).thenReturn(true, false);
        when(mockEduRs.getString("name")).thenReturn("Puzzle");
        when(mockEduRs.getString("type")).thenReturn("educational");
        when(mockEduRs.getInt("price")).thenReturn(120);
        when(mockEduRs.getInt("size")).thenReturn(25);
        when(mockEduRs.getString("material")).thenReturn("cardboard");
        when(mockEduRs.getInt("age_restrictions")).thenReturn(4);
        when(mockEduRs.getInt("number_of_details")).thenReturn(20);

        List<Toy> toys = loader.loadAll();
        assertEquals(1, toys.size());
        assertTrue(toys.get(0) instanceof EducationalToy);
        assertEquals("Puzzle", toys.get(0).getName());
    }
}
