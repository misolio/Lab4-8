package Control;

import Toy.Toy;
import Menu.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DeletToyTest {
    private List<Toy> toys;
    private DeletToy deletToy;
    private Scanner mockScanner;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        deletToy = new DeletToy(toys);
        mockScanner = mock(Scanner.class);
    }

    @Test
    public void testExecuteWithEmptyList() {
        deletToy.execute();
        assertEquals(0, toys.size());
    }

    @Test
    public void testExecuteWithValidChoice() {
        Toy toy1 = Mockito.mock(Toy.class);
        when(toy1.toString()).thenReturn("Toy1");

        Toy toy2 = Mockito.mock(Toy.class);
        when(toy2.toString()).thenReturn("Toy2");

        toys.add(toy1);
        toys.add(toy2);

        when(mockScanner.nextInt()).thenReturn(1);

        deletToy.setScanner(mockScanner); // Assuming you modify DeletToy to accept a scanner
        deletToy.execute();

        assertEquals(1, toys.size());
        assertEquals("Toy2", toys.get(0).toString());
    }

    @Test
    public void testExecuteWithInvalidChoice() {
        Toy toy1 = Mockito.mock(Toy.class);
        when(toy1.toString()).thenReturn("Toy1");

        Toy toy2 = Mockito.mock(Toy.class);
        when(toy2.toString()).thenReturn("Toy2");

        toys.add(toy1);
        toys.add(toy2);

        when(mockScanner.nextInt()).thenReturn(3);

        deletToy.setScanner(mockScanner); // Assuming you modify DeletToy to accept a scanner
        deletToy.execute();

        assertEquals(2, toys.size());
    }
}
