package File;

import Toy.PlushToy;
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

public class LoadPlushToyTest {
    private List<Toy> toys;
    private LoadPlushToy loadPlushToy;
    private BufferedReader mockBufferedReader;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        loadPlushToy = new LoadPlushToy(toys);
        mockBufferedReader = Mockito.mock(BufferedReader.class);
    }

    @Test
    public void testLoadPlushToys() throws IOException {
        String line1 = "Ведмедик,Іграшка,500,30,тканина,3,м'яка іграшка,ведмедик,true,40,25,20";
        String line2 = "Кролик,Іграшка,300,20,плюш,4,іграшка,кролик,false,35,20,15";

        when(mockBufferedReader.readLine())
                .thenReturn(line1)
                .thenReturn(line2)
                .thenReturn(null);

        try (BufferedReader br = mockBufferedReader) {
            loadPlushToy.load();
        }

        assertEquals(3, toys.size());

        PlushToy plushToy1 = (PlushToy) toys.get(0);
        assertEquals("Ведмедик", plushToy1.getName());
        assertEquals("Іграшка", plushToy1.getType());
        assertEquals(500, plushToy1.getPrice());
        assertEquals(30, plushToy1.getSize());
        assertEquals("тканина", plushToy1.getMaterial());
        assertEquals(3, plushToy1.getAgeRestrictions());
        assertEquals("м'яка іграшка", plushToy1.getGameType());
        assertEquals("ведмедик", plushToy1.getTypeOfToy());
        assertEquals(true, plushToy1.isPresenceOfSoundEffects());
        assertEquals(40, plushToy1.getHeight());
        assertEquals(25, plushToy1.getLength());
        assertEquals(20, plushToy1.getWidth());

        PlushToy plushToy2 = (PlushToy) toys.get(1);
        assertEquals("Кролик", plushToy2.getName());
        assertEquals("Іграшка", plushToy2.getType());
        assertEquals(300, plushToy2.getPrice());
        assertEquals(20, plushToy2.getSize());
        assertEquals("плюш", plushToy2.getMaterial());
        assertEquals(4, plushToy2.getAgeRestrictions());
        assertEquals("іграшка", plushToy2.getGameType());
        assertEquals("кролик", plushToy2.getTypeOfToy());
        assertEquals(false, plushToy2.isPresenceOfSoundEffects());
        assertEquals(35, plushToy2.getHeight());
        assertEquals(20, plushToy2.getLength());
        assertEquals(15, plushToy2.getWidth());
    }

}
