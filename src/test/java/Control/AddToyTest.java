package Control;

import Toy.*;
import File.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddToyTest {
    private List<Toy> toys;
    private AddToy addToy;
    private Scanner mockScanner;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        int budget = 2000;
        addToy = new AddToy(toys, budget);
        mockScanner = Mockito.mock(Scanner.class);
        addToy.setScanner(mockScanner);
        ToyLoader mockToyLoader = mock(ToyLoader.class);
        addToy.loaders.put(1, mockToyLoader);
    }

    @Test
    public void testAddToyWithinBudget() {
        Toy toy1 = Mockito.mock(BoardGames.class);
        when(toy1.getPrice()).thenReturn(1500);
        when(toy1.getName()).thenReturn("Монополія");

        Toy toy2 = Mockito.mock(BoardGames.class);
        when(toy2.getPrice()).thenReturn(500);
        when(toy2.getName()).thenReturn("Шахи");

        toys.add(toy1);
        toys.add(toy2);

        addToy.addToysWithinBudget(1);

        assertEquals(2, toys.size());
        assertEquals(1500, toy1.getPrice());
        assertEquals(500, toy2.getPrice());
        assertEquals(2000, addToy.getBudget()); // Budget should be 0 after adding both toys
    }

    @Test
    public void testAddToyExceedingBudget() {
        Toy toy1 = Mockito.mock(BoardGames.class);
        when(toy1.getPrice()).thenReturn(1500);
        when(toy1.getName()).thenReturn("Монополія");

        Toy toy2 = Mockito.mock(BoardGames.class);
        when(toy2.getPrice()).thenReturn(1000);
        when(toy2.getName()).thenReturn("Шахи");

        toys.add(toy1);
        toys.add(toy2);

        addToy.addToysWithinBudget(1);

        assertEquals(2, toys.size()); // Only one toy should be added within the budget
        assertEquals(1500, toy1.getPrice());
        assertEquals(2000, addToy.getBudget()); // Remaining budget should be 500
    }

    @Test
    public void testAddToyExactBudget() {
        Toy toy1 = Mockito.mock(BoardGames.class);
        when(toy1.getPrice()).thenReturn(2000);
        when(toy1.getName()).thenReturn("Монополія");

        toys.add(toy1);

        addToy.addToysWithinBudget(1);

        assertEquals(1, toys.size());
        assertEquals(2000, toy1.getPrice());
        assertEquals(2000, addToy.getBudget()); // Budget should be 0 after adding the toy
    }

    @Test
    public void testAddNoToys() {
        addToy.addToysWithinBudget(1);

        assertEquals(0, toys.size());
        assertEquals(2000, addToy.getBudget()); // Budget should remain the same
    }

    @Test
    public void testCanAddToyWithinBudget() {
        Toy toy = Mockito.mock(Toy.class);
        when(toy.getPrice()).thenReturn(1500);
        when(toy.getName()).thenReturn("Монополія");

        boolean result = addToy.canAddToy(toy);

        assertTrue(result);
        assertEquals(500, addToy.getBudget());
    }

    @Test
    public void testCanAddToyExactBudget() {
        Toy toy = Mockito.mock(Toy.class);
        when(toy.getPrice()).thenReturn(2000);
        when(toy.getName()).thenReturn("Каркассон");

        boolean result = addToy.canAddToy(toy);

        assertTrue(result);
        assertEquals(0, addToy.getBudget());
    }


}
