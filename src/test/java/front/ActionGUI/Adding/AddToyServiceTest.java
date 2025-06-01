package front.ActionGUI.Adding;

import back.Loader.Loading;
import back.Toy.Toy;
import javafx.scene.control.Alert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddToyServiceTest {
    private ToyDialogView mockView;
    private Connection mockConn;
    private AddToyService service;

    private Toy cheapToy;
    private Toy expensiveToy;

    @BeforeEach
    void setUp() {
        mockView = mock(ToyDialogView.class);
        mockConn = mock(Connection.class);

        cheapToy = mock(Toy.class);
        when(cheapToy.getName()).thenReturn("Cheap Toy");
        when(cheapToy.getPrice()).thenReturn(10);

        expensiveToy = mock(Toy.class);
        when(expensiveToy.getName()).thenReturn("Expensive Toy");
        when(expensiveToy.getPrice()).thenReturn(1000);
        Map<Integer, Loading> mockLoaders = new HashMap<>();
        mockLoaders.put(1, new Loading() {
            @Override
            public Toy getToyById(int id) {
                return cheapToy; // або null, якщо метод не потрібен
            }

            @Override
            public List<Toy> loadAll() {
                return List.of(cheapToy, expensiveToy);
            }

            @Override
            public List<Toy> load(int toyId) {
                return List.of(cheapToy); // або Collections.emptyList()
            }
        });
        service = new AddToyService(new ArrayList<>(), 100, mockConn, mockView, mockLoaders);

    }

    @Test
    void test_addAffordableToy() {
        when(mockView.promptToyType()).thenReturn(1, -1); // 1: one round, -1: exit
        when(mockView.promptToySelection(any(), eq(100)))
                .thenReturn(List.of(cheapToy));
        when(mockView.confirm(any())).thenReturn(false); // exit after one round

        service.run();

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockView).showAlert(eq(Alert.AlertType.INFORMATION), captor.capture());

        String result = captor.getValue();
        assertTrue(result.contains("✔ Cheap Toy"));
        assertFalse(result.contains("✖"));
    }

    @Test
    void test_addExpensiveToyFails() {
        when(mockView.promptToyType()).thenReturn(1, -1); // one round
        when(mockView.promptToySelection(any(), eq(100)))
                .thenReturn(List.of(expensiveToy));
        when(mockView.confirm(any())).thenReturn(false);

        service.run();

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockView).showAlert(eq(Alert.AlertType.INFORMATION), captor.capture());

        String result = captor.getValue();
        assertTrue(result.contains("✖ Недостатньо бюджету для Expensive Toy"));
    }

    @Test
    void test_cancelAtTypePrompt() {
        when(mockView.promptToyType()).thenReturn(-1);

        service.run();

        verify(mockView, never()).promptToySelection(any(), anyInt());
    }

    @Test
    void test_getBudgetAfterAdding() {
        when(mockView.promptToyType()).thenReturn(1, -1);
        when(mockView.promptToySelection(any(), eq(100)))
                .thenReturn(List.of(cheapToy));
        when(mockView.confirm(any())).thenReturn(false);

        service.run();

        // 100 - 10 = 90
        assertEquals(90, service.getBudget());
    }
}
