package File;

import Toy.EducationalToy;
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

public class LoadEducationalToysTest {
    private List<Toy> toys;
    private LoadEducationalToys loadEducationalToys;
    private BufferedReader mockBufferedReader;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        loadEducationalToys = new LoadEducationalToys(toys);
        mockBufferedReader = Mockito.mock(BufferedReader.class);
    }

    @Test
    public void testLoadEducationalToys() throws IOException {
        String line1 = "Конструктор Лего,LEGO,500,15,пластик,6,навчальна гра,конструктор,100";
        String line2 = "Арифметика для малюків,MathToys,300,10,дерево,4,логічна гра,цифровий набір,50";

        when(mockBufferedReader.readLine())
                .thenReturn(line1)
                .thenReturn(line2)
                .thenReturn(null);

        try (BufferedReader br = mockBufferedReader) {
            loadEducationalToys.load();
        }

        assertEquals(3, toys.size());

        EducationalToy eduToy1 = (EducationalToy) toys.get(0);
        assertEquals("Конструктор Лего", eduToy1.getName());
        assertEquals("LEGO", eduToy1.getType());
        assertEquals(500, eduToy1.getPrice());
        assertEquals(15, eduToy1.getSize());
        assertEquals("пластик", eduToy1.getMaterial());
        assertEquals(6, eduToy1.getAgeRestrictions());
        assertEquals("навчальна гра", eduToy1.getGameType());
        assertEquals("конструктор", eduToy1.getTypeOfToy());
        assertEquals(100, eduToy1.getNumberOfDetails());

        EducationalToy eduToy2 = (EducationalToy) toys.get(1);
        assertEquals("Арифметика для малюків", eduToy2.getName());
        assertEquals("MathToys", eduToy2.getType());
        assertEquals(300, eduToy2.getPrice());
        assertEquals(10, eduToy2.getSize());
        assertEquals("дерево", eduToy2.getMaterial());
        assertEquals(4, eduToy2.getAgeRestrictions());
        assertEquals("логічна гра", eduToy2.getGameType());
        assertEquals("цифровий набір", eduToy2.getTypeOfToy());
        assertEquals(50, eduToy2.getNumberOfDetails());
    }

}
