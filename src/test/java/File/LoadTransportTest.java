package File;

import Toy.Transport;
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

public class LoadTransportTest {
    private List<Toy> toys;
    private LoadTransport loadTransport;
    private BufferedReader mockBufferedReader;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        loadTransport = new LoadTransport(toys);
        mockBufferedReader = Mockito.mock(BufferedReader.class);
    }

    @Test
    public void testLoadTransport() throws IOException {
        String line1 = "Автомобіль,Машинка,1000,20,пластик,5,транспорт,автомобіль,15,30,10,4,true";
        String line2 = "Поїзд,Іграшка,1500,25,метал,6,транспорт,поїзд,20,40,12,8,false";

        when(mockBufferedReader.readLine())
                .thenReturn(line1)
                .thenReturn(line2)
                .thenReturn(null);

        try (BufferedReader br = mockBufferedReader) {
            loadTransport.load();
        }

        assertEquals(3, toys.size());

        Transport transport1 = (Transport) toys.get(0);
        assertEquals("Автомобіль", transport1.getName());
        assertEquals("Машинка", transport1.getType());
        assertEquals(1000, transport1.getPrice());
        assertEquals(20, transport1.getSize());
        assertEquals("пластик", transport1.getMaterial());
        assertEquals(5, transport1.getAgeRestrictions());
        assertEquals("транспорт", transport1.getGameType());
        assertEquals("автомобіль", transport1.getTypeOfTransport());
        assertEquals(15, transport1.getHeight());
        assertEquals(30, transport1.getLength());
        assertEquals(10, transport1.getWidth());
        assertEquals(4, transport1.getNumberOfWheels());
        assertEquals(true, transport1.isOnElectricControl());

        Transport transport2 = (Transport) toys.get(1);
        assertEquals("Поїзд", transport2.getName());
        assertEquals("Іграшка", transport2.getType());
        assertEquals(1500, transport2.getPrice());
        assertEquals(25, transport2.getSize());
        assertEquals("метал", transport2.getMaterial());
        assertEquals(6, transport2.getAgeRestrictions());
        assertEquals("транспорт", transport2.getGameType());
        assertEquals("поїзд", transport2.getTypeOfTransport());
        assertEquals(20, transport2.getHeight());
        assertEquals(40, transport2.getLength());
        assertEquals(12, transport2.getWidth());
        assertEquals(8, transport2.getNumberOfWheels());
        assertEquals(false, transport2.isOnElectricControl());
    }
}
