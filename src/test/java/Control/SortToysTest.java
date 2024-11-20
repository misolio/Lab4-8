package Control;

import Toy.Toy;
import Sort.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SortToysTest {
    private List<Toy> toys;
    private SortToys sortToys;
    private Scanner mockScanner;
    private Toy toy1; // Ensure these are class-level variables
    private Toy toy2; // Ensure these are class-level variables

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();

        // Creating mock Toy objects
        toy1 = mock(Toy.class);
        when(toy1.getName()).thenReturn("Toy1");
        when(toy1.getPrice()).thenReturn(20);
        when(toy1.getSize()).thenReturn(5);
        when(toy1.getMaterial()).thenReturn("Plastic");
        when(toy1.getAgeRestrictions()).thenReturn(3);

        toy2 = mock(Toy.class);
        when(toy2.getName()).thenReturn("Toy2");
        when(toy2.getPrice()).thenReturn(10);
        when(toy2.getSize()).thenReturn(10);
        when(toy2.getMaterial()).thenReturn("Wood");
        when(toy2.getAgeRestrictions()).thenReturn(5);

        toys.add(toy1);
        toys.add(toy2);

        sortToys = new SortToys(toys);
        mockScanner = Mockito.mock(Scanner.class);
    }

    @Test
    public void testSortByPrice() {
        when(mockScanner.nextInt()).thenReturn(1); // Choose to sort by price

        // Inject mock scanner
        sortToys.setScanner(mockScanner);

        // Perform sorting
        sortToys.execute();

        // Expected sorted list
        List<Toy> sortedToys = new ArrayList<>();
        sortedToys.add(toy2); // Toy with lower price should come first
        sortedToys.add(toy1);

        // Verify sorting result
        assertEquals(sortedToys, toys);
    }

    @Test
    public void testSortBySize() {
        when(mockScanner.nextInt()).thenReturn(2); // Choose to sort by size

        // Inject mock scanner
        sortToys.setScanner(mockScanner);

        // Perform sorting
        sortToys.execute();

        // Expected sorted list
        List<Toy> sortedToys = new ArrayList<>();
        sortedToys.add(toy1); // Toy with smaller size should come first
        sortedToys.add(toy2);

        // Verify sorting result
        assertEquals(sortedToys, toys);
    }

    @Test
    public void testSortByMaterial() {
        when(mockScanner.nextInt()).thenReturn(3); // Choose to sort by material

        // Inject mock scanner
        sortToys.setScanner(mockScanner);

        // Perform sorting
        sortToys.execute();

        // Expected sorted list
        List<Toy> sortedToys = new ArrayList<>();
        sortedToys.add(toy1); // "Plastic" comes before "Wood"
        sortedToys.add(toy2);

        // Verify sorting result
        assertEquals(sortedToys, toys);
    }

    @Test
    public void testSortByName() {
        when(mockScanner.nextInt()).thenReturn(4); // Choose to sort by name

        // Inject mock scanner
        sortToys.setScanner(mockScanner);

        // Perform sorting
        sortToys.execute();

        // Expected sorted list
        List<Toy> sortedToys = new ArrayList<>();
        sortedToys.add(toy1); // "Toy1" comes before "Toy2"
        sortedToys.add(toy2);

        // Verify sorting result
        assertEquals(sortedToys, toys);
    }

    @Test
    public void testSortByAgeRestrictions() {
        when(mockScanner.nextInt()).thenReturn(5); // Choose to sort by age restrictions

        // Inject mock scanner
        sortToys.setScanner(mockScanner);

        // Perform sorting
        sortToys.execute();

        // Expected sorted list
        List<Toy> sortedToys = new ArrayList<>();
        sortedToys.add(toy1); // Age restriction 3 comes before 5
        sortedToys.add(toy2);

        // Verify sorting result
        assertEquals(sortedToys, toys);
    }
}
