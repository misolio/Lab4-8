package back.Loader;

import back.Toy.PlushToy;
import back.Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlushToyLoaderTest {

    private Connection conn;
    private PlushToyLoader loader;
    private PreparedStatement stmt;
    private ResultSet toyRs;
    private ResultSet plushRs;

    @BeforeEach
    void setUp() throws SQLException {
        conn = mock(Connection.class);
        loader = new PlushToyLoader(conn);
        stmt = mock(PreparedStatement.class);
        toyRs = mock(ResultSet.class);
        plushRs = mock(ResultSet.class);
    }

    @Test
    void testGetToyByIdReturnsPlushToy() throws Exception {
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(toyRs).thenReturn(plushRs);

        when(toyRs.next()).thenReturn(true);
        when(toyRs.getString("name")).thenReturn("Teddy");
        when(toyRs.getString("type")).thenReturn("plush");
        when(toyRs.getInt("price")).thenReturn(150);
        when(toyRs.getInt("size")).thenReturn(35);
        when(toyRs.getString("material")).thenReturn("cotton");
        when(toyRs.getInt("age_restrictions")).thenReturn(3);

        when(plushRs.next()).thenReturn(true);
        when(plushRs.getBoolean("presence_of_sound_effects")).thenReturn(true);
        when(plushRs.getInt("height")).thenReturn(40);
        when(plushRs.getInt("lengthh")).thenReturn(25);
        when(plushRs.getInt("width")).thenReturn(20);

        Toy result = loader.getToyById(1);

        assertNotNull(result);
        assertTrue(result instanceof PlushToy);
        assertEquals("Teddy", result.getName());
    }

    @Test
    void testLoadReturnsEmptyListWhenToyNotFound() throws Exception {
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(toyRs);
        when(toyRs.next()).thenReturn(false);

        List<Toy> result = loader.load(1);
        assertTrue(result.isEmpty());
    }

    @Test
    void testLoadAllReturnsPlushToyList() throws Exception {
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(plushRs);

        when(plushRs.next()).thenReturn(true, false);
        when(plushRs.getString("name")).thenReturn("Bunny");
        when(plushRs.getString("type")).thenReturn("plush");
        when(plushRs.getInt("price")).thenReturn(120);
        when(plushRs.getInt("size")).thenReturn(28);
        when(plushRs.getString("material")).thenReturn("fleece");
        when(plushRs.getInt("age_restrictions")).thenReturn(2);
        when(plushRs.getBoolean("presence_of_sound_effects")).thenReturn(false);
        when(plushRs.getInt("height")).thenReturn(30);
        when(plushRs.getInt("lengthh")).thenReturn(15);
        when(plushRs.getInt("width")).thenReturn(10);

        List<Toy> toys = loader.loadAll();
        assertEquals(1, toys.size());
        assertTrue(toys.get(0) instanceof PlushToy);
        assertEquals("Bunny", toys.get(0).getName());
    }
}
