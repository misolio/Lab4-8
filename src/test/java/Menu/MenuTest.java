package Menu;

import Control.AddToy;
import Control.DeletToy;
import Control.FindToy;
import Control.SortToys;
import Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class MenuTest {
    private List<Toy> toys;
    private Menu menu;
    private Scanner mockScanner;

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        mockScanner = Mockito.mock(Scanner.class);
        System.setIn(new java.io.ByteArrayInputStream("1000\n".getBytes())); // Set initial budget input
        menu = new Menu(toys);
        System.setIn(System.in); // Reset System.in to default after setup
    }

    @Test
    public void testExecuteAddToy() {
        AddToy mockAddToy = Mockito.mock(AddToy.class);
        menu.menuItems.put(1, mockAddToy);

        when(mockScanner.nextInt()).thenReturn(1);

        menu.setScanner(mockScanner);
        menu.executeCommand(1);

        verify(mockAddToy, times(1)).execute();
    }

    @Test
    public void testExecuteSortToys() {
        SortToys mockSortToys = Mockito.mock(SortToys.class);
        menu.menuItems.put(2, mockSortToys);

        when(mockScanner.nextInt()).thenReturn(2);

        menu.setScanner(mockScanner);
        menu.executeCommand(2);

        verify(mockSortToys, times(1)).execute();
    }

    @Test
    public void testExecuteFindToy() {
        FindToy mockFindToy = Mockito.mock(FindToy.class);
        menu.menuItems.put(3, mockFindToy);

        when(mockScanner.nextInt()).thenReturn(3);

        menu.setScanner(mockScanner);
        menu.executeCommand(3);

        verify(mockFindToy, times(1)).execute();
    }

    @Test
    public void testExecuteDeletToy() {
        DeletToy mockDeletToy = Mockito.mock(DeletToy.class);
        menu.menuItems.put(4, mockDeletToy);

        when(mockScanner.nextInt()).thenReturn(4);

        menu.setScanner(mockScanner);
        menu.executeCommand(4);

        verify(mockDeletToy, times(1)).execute();
    }

    @Test
    public void testExecuteInvalidCommand() {
        when(mockScanner.nextInt()).thenReturn(99);

        menu.setScanner(mockScanner);
        menu.executeCommand(99);

        // Verify that no command is executed
        verifyNoInteractions(mockScanner);
    }
}
