package back.Loader;

import back.Toy.Doll;
import back.Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DollLoaderTest {

    private Connection conn;
    private PreparedStatement toyStmt;
    private PreparedStatement dollStmt;
    private ResultSet toyRs;
    private ResultSet dollRs;
    private DollLoader loader;

    @BeforeEach
    void setUp() throws Exception {
        conn = mock(Connection.class);
        toyStmt = mock(PreparedStatement.class);
        dollStmt = mock(PreparedStatement.class);
        toyRs = mock(ResultSet.class);
        dollRs = mock(ResultSet.class);
        loader = new DollLoader(conn);
    }

    @Test
    void testGetToyById_ReturnsDoll() throws Exception {
        when(conn.prepareStatement("SELECT * FROM toy WHERE toy_id = ?")).thenReturn(toyStmt);
        when(conn.prepareStatement("SELECT * FROM doll WHERE doll_id = ?")).thenReturn(dollStmt);

        when(toyStmt.executeQuery()).thenReturn(toyRs);
        when(toyRs.next()).thenReturn(true);
        when(toyRs.getString("name")).thenReturn("Barbie");
        when(toyRs.getString("type")).thenReturn("Doll");
        when(toyRs.getInt("price")).thenReturn(500);
        when(toyRs.getInt("size")).thenReturn(20);
        when(toyRs.getString("material")).thenReturn("Plastic");
        when(toyRs.getInt("age_restrictions")).thenReturn(3);

        when(dollStmt.executeQuery()).thenReturn(dollRs);
        when(dollRs.next()).thenReturn(true);
        when(dollRs.getBoolean("sex")).thenReturn(true);
        when(dollRs.getInt("height")).thenReturn(30);
        when(dollRs.getInt("lengthh")).thenReturn(10);
        when(dollRs.getInt("width")).thenReturn(5);
        when(dollRs.getString("clothes_color")).thenReturn("Pink");
        when(dollRs.getString("hair_color")).thenReturn("Blonde");

        Toy toy = loader.getToyById(1);

        assertNotNull(toy);
        assertInstanceOf(Doll.class, toy);
        assertEquals("Barbie", toy.getName());
    }

    @Test
    void testGetToyById_ToyNotFound() throws Exception {
        when(conn.prepareStatement(anyString())).thenReturn(toyStmt);
        when(toyStmt.executeQuery()).thenReturn(toyRs);
        when(toyRs.next()).thenReturn(false); // Не знайдено

        Toy toy = loader.getToyById(99);
        assertNull(toy);
    }

    @Test
    void testLoadAll_ReturnsList() throws Exception {
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);

        when(rs.getString("name")).thenReturn("Polly");
        when(rs.getString("type")).thenReturn("Doll");
        when(rs.getInt("price")).thenReturn(250);
        when(rs.getInt("size")).thenReturn(18);
        when(rs.getString("material")).thenReturn("Vinyl");
        when(rs.getInt("age_restrictions")).thenReturn(4);
        when(rs.getBoolean("sex")).thenReturn(false);
        when(rs.getInt("height")).thenReturn(25);
        when(rs.getInt("lengthh")).thenReturn(9);
        when(rs.getInt("width")).thenReturn(4);
        when(rs.getString("clothes_color")).thenReturn("Yellow");
        when(rs.getString("hair_color")).thenReturn("Brown");

        List<Toy> toys = loader.loadAll();

        assertEquals(1, toys.size());
        assertInstanceOf(Doll.class, toys.get(0));
        assertEquals("Polly", toys.get(0).getName());
    }

    @Test
    void testLoadAll_Empty() throws Exception {
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false); // no data

        List<Toy> toys = loader.loadAll();

        assertNotNull(toys);
        assertTrue(toys.isEmpty());
    }
}
