package Control;

import Toy.Toy;
import Filter.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FindToyTest {
    private List<Toy> toys;
    private FindToy findToy;
    private Scanner mockScanner;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();

        // Creating mock Toy objects
        Toy toy1 = mock(Toy.class);
        when(toy1.getName()).thenReturn("Toy1");
        when(toy1.getType()).thenReturn("Type1");
        when(toy1.getPrice()).thenReturn(10);
        when(toy1.getSize()).thenReturn(5);
        when(toy1.getMaterial()).thenReturn("Plastic");
        when(toy1.getAgeRestrictions()).thenReturn(3);
        when(toy1.getGameType()).thenReturn("Game1");

        Toy toy2 = mock(Toy.class);
        when(toy2.getName()).thenReturn("Toy2");
        when(toy2.getType()).thenReturn("Type2");
        when(toy2.getPrice()).thenReturn(20);
        when(toy2.getSize()).thenReturn(10);
        when(toy2.getMaterial()).thenReturn("Wood");
        when(toy2.getAgeRestrictions()).thenReturn(5);
        when(toy2.getGameType()).thenReturn("Game2");

        toys.add(toy1);
        toys.add(toy2);

        findToy = new FindToy(toys);
        mockScanner = Mockito.mock(Scanner.class);
    }

    @Test
    public void testFindToyByName() {
        when(mockScanner.nextInt()).thenReturn(1); // Choose parameter
        when(mockScanner.nextLine()).thenReturn("Toy1"); // Search by name

        findToy.setScanner(mockScanner); // Set the mocked scanner
        findToy.execute();

        List<Toy> filteredToys = new ArrayList<>();
        filteredToys.add(toys.get(0)); // Mock toy1

        assertEquals(filteredToys.toString(), findToy.getFilteredToys().toString());
    }

    @Test
    public void testFindToyByPrice() {
        when(mockScanner.nextInt()).thenReturn(2); // Choose parameter
        when(mockScanner.nextLine()).thenReturn("19"); // Search by price

        findToy.setScanner(mockScanner); // Set the mocked scanner
        findToy.execute();

        List<Toy> filteredToys = new ArrayList<>();
        filteredToys.add(toys.get(0));

        assertEquals(filteredToys.toString(), findToy.getFilteredToys().toString());
    }

    @Test
    public void testFindToyByMaterial() {
        when(mockScanner.nextInt()).thenReturn(3);
        when(mockScanner.nextLine()).thenReturn("Wood");

        findToy.setScanner(mockScanner);
        findToy.execute();

        List<Toy> filteredToys = new ArrayList<>();
        filteredToys.add(toys.get(1));

        assertEquals(filteredToys.toString(), findToy.getFilteredToys().toString());
    }

    @Test
    public void testFindToyBySize() {
        when(mockScanner.nextInt()).thenReturn(4);
        when(mockScanner.nextLine()).thenReturn("20");

        findToy.setScanner(mockScanner);
        findToy.execute();

        List<Toy> filteredToys = new ArrayList<>();
        filteredToys.add(toys.get(1));

        assertEquals("[]", findToy.getFilteredToys().toString());
    }

    @Test
    public void testFindToyByAgeRestrictions() {
        when(mockScanner.nextInt()).thenReturn(5);
        when(mockScanner.nextLine()).thenReturn("3");

        findToy.setScanner(mockScanner);
        findToy.execute();

        List<Toy> filteredToys = new ArrayList<>();
        filteredToys.add(toys.get(0));
        assertEquals(filteredToys.toString(), findToy.getFilteredToys().toString());
    }
}
