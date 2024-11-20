import Menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Toy.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MainTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Menu mockMenu;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        mockMenu = Mockito.mock(Menu.class);
    }

    @Test
    public void testMainMenu() {
        String userInput = "1\n5\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        List<Toy> toys = new ArrayList<>();
        Scanner scanner = new Scanner(inputStream);
        Main.runProgram(mockMenu, scanner);

        verify(mockMenu, times(2)).displayMenu();
        verify(mockMenu, times(1)).executeCommand(1);

        String expectedOutput = "Виберіть команду: \n" +
                "Виберіть команду: \n" +
                "Програма завершена.";

        String actualOutput = outputStreamCaptor.toString().trim().replace("\r\n", "\n").replace("\r", "\n");

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testExitProgram() {
        String userInput = "5\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        List<Toy> toys = new ArrayList<>();
        Scanner scanner = new Scanner(inputStream);
        Main.runProgram(mockMenu, scanner);

        verify(mockMenu, times(1)).displayMenu();

        String expectedOutput = "Виберіть команду: \nПрограма завершена.";

        String actualOutput = outputStreamCaptor.toString().trim().replace("\r\n", "\n").replace("\r", "\n");

        assertEquals(expectedOutput, actualOutput);
    }
}
