package back.Loader;

import back.Toy.Toy;
import back.Toy.Transport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransportLoaderTest {

    private Connection conn;
    private TransportLoader loader;
    private PreparedStatement stmt;
    private ResultSet toyRs;
    private ResultSet transportRs;

    @BeforeEach
    void setUp() throws Exception {
        conn = mock(Connection.class);
        loader = new TransportLoader(conn);
        stmt = mock(PreparedStatement.class);
        toyRs = mock(ResultSet.class);
        transportRs = mock(ResultSet.class);
    }

    @Test
    void testGetToyByIdReturnsTransport() throws Exception {
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(toyRs).thenReturn(transportRs);

        when(toyRs.next()).thenReturn(true);
        when(toyRs.getString("name")).thenReturn("Truck");
        when(toyRs.getString("type")).thenReturn("transport");
        when(toyRs.getInt("price")).thenReturn(300);
        when(toyRs.getInt("size")).thenReturn(40);
        when(toyRs.getString("material")).thenReturn("metal");
        when(toyRs.getInt("age_restrictions")).thenReturn(5);

        when(transportRs.next()).thenReturn(true);
        when(transportRs.getString("type_of_transport")).thenReturn("car");
        when(transportRs.getInt("height")).thenReturn(12);
        when(transportRs.getInt("lengthh")).thenReturn(30);
        when(transportRs.getInt("width")).thenReturn(15);
        when(transportRs.getInt("number_of_wheels")).thenReturn(4);
        when(transportRs.getBoolean("on_electric_control")).thenReturn(true);

        Toy toy = loader.getToyById(1);

        assertNotNull(toy);
        assertTrue(toy instanceof Transport);
        assertEquals("Truck", toy.getName());
    }

    @Test
    void testLoadReturnsEmptyListWhenNotFound() throws Exception {
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(toyRs);
        when(toyRs.next()).thenReturn(false);

        List<Toy> toys = loader.load(999);
        assertTrue(toys.isEmpty());
    }

    @Test
    void testLoadAllReturnsList() throws Exception {
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(transportRs);

        when(transportRs.next()).thenReturn(true, false);
        when(transportRs.getString("name")).thenReturn("Bike");
        when(transportRs.getString("type")).thenReturn("transport");
        when(transportRs.getInt("price")).thenReturn(200);
        when(transportRs.getInt("size")).thenReturn(25);
        when(transportRs.getString("material")).thenReturn("plastic");
        when(transportRs.getInt("age_restrictions")).thenReturn(4);
        when(transportRs.getString("type_of_transport")).thenReturn("bike");
        when(transportRs.getInt("height")).thenReturn(10);
        when(transportRs.getInt("lengthh")).thenReturn(20);
        when(transportRs.getInt("width")).thenReturn(8);
        when(transportRs.getInt("number_of_wheels")).thenReturn(2);
        when(transportRs.getBoolean("on_electric_control")).thenReturn(false);

        List<Toy> toys = loader.loadAll();
        assertEquals(1, toys.size());
        assertTrue(toys.get(0) instanceof Transport);
        assertEquals("Bike", toys.get(0).getName());
    }
}
