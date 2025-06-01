package front.ActionGUI.Adding;

import back.Toy.Toy;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AddToyDialogTest {

    @Test
    public void testExecuteCallsServiceRun() {
        AddToyService mockService = mock(AddToyService.class);
        AddToyDialog dialog = new AddToyDialog(mockService);

        dialog.execute();

        verify(mockService).run();
    }
    @Test
    public void testConstructorInitializesService() {
        List<Toy> toys = new ArrayList<>();
        Connection mockConn = mock(Connection.class);

        AddToyDialog dialog = new AddToyDialog(toys, 1000, mockConn);

        // Просто перевіримо, що об'єкт створено
        assertNotNull(dialog);
    }


    @Test
    public void testGetBudgetReturnsCorrectValue() {
        AddToyService mockService = mock(AddToyService.class);
        when(mockService.getBudget()).thenReturn(750);

        AddToyDialog dialog = new AddToyDialog(mockService);

        assertEquals(750, dialog.getBudget());
    }

}
