package File;

import Toy.Doll;
import Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoadDollsTest {
    private List<Toy> toys;
    private LoadDolls loadDolls;
    private BufferedReader mockBufferedReader;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        mockBufferedReader = Mockito.mock(BufferedReader.class);
        loadDolls = new LoadDolls(toys);
    }

    @Test
    public void testLoadDolls() throws IOException {
        String line1 = "Барбі,Лялька,150,200,Пластик,3,Лялькова гра,Фэшн,true,30,10,5,Рожевий,Блондинка";
        String line2 = "Кен,Лялька,140,180,Пластик,3,Лялькова гра,Спортивний,false,32,12,6,Синій,Коричневий";
        String line3 = "Ариeль,Лялька,200,220,Пластик,5,Лялькова гра,Принцеса,true,28,10,5,Червоний,Оранжевий";

        when(mockBufferedReader.readLine())
                .thenReturn(line1)
                .thenReturn(line2)
                .thenReturn(line3)
                .thenReturn(null);

        loadDolls.load();

        assertEquals(3, toys.size());

        Doll doll1 = (Doll) toys.get(0);
        assertEquals("Барбі", doll1.getName());
        assertEquals("Лялька", doll1.getType());
        assertEquals(150, doll1.getPrice());
        assertEquals(200, doll1.getSize());
        assertEquals("Пластик", doll1.getMaterial());
        assertEquals(3, doll1.getAgeRestrictions());
        assertEquals("Лялькова гра", doll1.getGameType());
        assertEquals("Фэшн", doll1.getTypeOfDoll());
        assertEquals(true, doll1.isSex());
        assertEquals(30, doll1.getHeight());
        assertEquals(10, doll1.getLength());
        assertEquals(5, doll1.getWidth());
        assertEquals("Рожевий", doll1.getClothesColor());
        assertEquals("Блондинка", doll1.getHairColor());

        Doll doll2 = (Doll) toys.get(1);
        assertEquals("Кен", doll2.getName());
        assertEquals("Лялька", doll2.getType());
        assertEquals(140, doll2.getPrice());
        assertEquals(180, doll2.getSize());
        assertEquals("Пластик", doll2.getMaterial());
        assertEquals(3, doll2.getAgeRestrictions());
        assertEquals("Лялькова гра", doll2.getGameType());
        assertEquals("Спортивний", doll2.getTypeOfDoll());
        assertEquals(false, doll2.isSex());
        assertEquals(32, doll2.getHeight());
        assertEquals(12, doll2.getLength());
        assertEquals(6, doll2.getWidth());
        assertEquals("Синій", doll2.getClothesColor());
        assertEquals("Коричневий", doll2.getHairColor());

        Doll doll3 = (Doll) toys.get(2);
        assertEquals("Ариeль", doll3.getName());
        assertEquals("Лялька", doll3.getType());
        assertEquals(200, doll3.getPrice());
        assertEquals(220, doll3.getSize());
        assertEquals("Пластик", doll3.getMaterial());
        assertEquals(5, doll3.getAgeRestrictions());
        assertEquals("Лялькова гра", doll3.getGameType());
        assertEquals("Принцеса", doll3.getTypeOfDoll());
        assertEquals(true, doll3.isSex());
        assertEquals(28, doll3.getHeight());
        assertEquals(10, doll3.getLength());
        assertEquals(5, doll3.getWidth());
        assertEquals("Червоний", doll3.getClothesColor());
        assertEquals("Оранжевий", doll3.getHairColor());
    }

}
