package front.ActionGUI.Finder;

import back.Control.ToyFinder;
import back.Toy.FakeToy;
import back.Toy.Toy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;

import static org.mockito.Mockito.*;

class FindToyDialogTest {

    private List<Toy> toys;
    private ToySearchDialogUI dialogUI;
    private Consumer<List<Toy>> displayCallback;

    @BeforeEach
    void setUp() {
        toys = new ArrayList<>();
        toys.add(new FakeToy("М'яч", 10));
        toys.add(new FakeToy("Лялька", 20));

        dialogUI = mock(ToySearchDialogUI.class);
        displayCallback = mock(Consumer.class);
    }

    @Test
    void testSearchWithValidParam() {
        // Given
        when(dialogUI.askNumberOfParams()).thenReturn(Optional.of(1));
        when(dialogUI.chooseParamName(anyList(), eq(0))).thenReturn(Optional.of("1. Назва"));
        when(dialogUI.askParamValue("1. Назва")).thenReturn(Optional.of("М'яч"));

        // When
        FindToyDialog dialog = new FindToyDialog(toys, displayCallback, dialogUI);
        dialog.execute();

        // Then
        verify(displayCallback).accept(argThat(filtered ->
                filtered.size() == 1 && filtered.get(0).getName().equals("М'яч")
        ));
    }

    @Test
    void testSearchCancelledByUserAtStart() {
        // Користувач закрив діалог без введення значення
        when(dialogUI.askNumberOfParams()).thenReturn(Optional.empty());

        FindToyDialog dialog = new FindToyDialog(toys, displayCallback, dialogUI);
        dialog.execute();

        verify(displayCallback, never()).accept(any());
    }

    @Test
    void testInvalidNumberOfParams() {
        when(dialogUI.askNumberOfParams()).thenReturn(Optional.of(5)); // Невалідне значення

        FindToyDialog dialog = new FindToyDialog(toys, displayCallback, dialogUI);
        dialog.execute();

        verify(dialogUI).showError("Кількість параметрів повинна бути від 1 до 3.");
        verify(displayCallback, never()).accept(any());
    }

    @Test
    void testSearchWithNoMatches() {
        when(dialogUI.askNumberOfParams()).thenReturn(Optional.of(1));
        when(dialogUI.chooseParamName(anyList(), eq(0))).thenReturn(Optional.of("1. Назва"));
        when(dialogUI.askParamValue("1. Назва")).thenReturn(Optional.of("Невідома"));

        FindToyDialog dialog = new FindToyDialog(toys, displayCallback, dialogUI);
        dialog.execute();

        verify(displayCallback).accept(Collections.emptyList());
    }

    @Test
    void testFilterFails_showsError() {
        ToyFinder mockFinder = mock(ToyFinder.class);
        when(mockFinder.filterBy(anyInt(), anyString())).thenReturn(false);
        when(mockFinder.getFilteredToys()).thenReturn(Collections.emptyList());

        when(dialogUI.askNumberOfParams()).thenReturn(Optional.of(1));
        when(dialogUI.chooseParamName(anyList(), eq(0))).thenReturn(Optional.of("1. Назва"));
        when(dialogUI.askParamValue("1. Назва")).thenReturn(Optional.of("Test"));

        FindToyDialog dialog = new FindToyDialog(mockFinder, displayCallback, dialogUI);
        dialog.execute();

        verify(dialogUI).showError(contains("Невдалось застосувати фільтр"));
    }

}
