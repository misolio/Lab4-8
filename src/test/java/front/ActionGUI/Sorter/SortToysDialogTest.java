package front.ActionGUI.Sorter;

import back.Control.ToySorter;
import back.Toy.Toy;
import front.ActionGUI.SortDialogUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SortToysDialogTest {

    private SortDialogUI dialogUI;
    private SortToysDialog sortToysDialog;
    private List<Toy> dummyToys;

    @BeforeEach
    void setUp() {
        dialogUI = mock(SortDialogUI.class);
        dummyToys = List.of(mock(Toy.class), mock(Toy.class));
        sortToysDialog = new SortToysDialog(dummyToys, dialogUI);
    }

    @Test
    void testExecute_WithValidOption_ShouldSortAndRefresh() {
        Map<String, Integer> options = ToySorter.getOptionsMap();
        String firstLabel = options.keySet().iterator().next();
        int sortKey = options.get(firstLabel);

        when(dialogUI.askSortOption(anyList())).thenReturn(Optional.of(firstLabel));

        ToySorter sorter = mock(ToySorter.class);
        when(sorter.sortBy(sortKey)).thenReturn(true);

        SortToysDialog dialog = new SortToysDialog(sorter, dialogUI);
        Runnable refresh = mock(Runnable.class);
        dialog.setRefreshCallback(refresh);

        dialog.execute();

        verify(dialogUI).askSortOption(anyList());
        verify(sorter).sortBy(sortKey);
        verify(refresh).run();
    }

    @Test
    void testExecute_WithEmptyOption_ShouldDoNothing() {
        when(dialogUI.askSortOption(anyList())).thenReturn(Optional.empty());

        sortToysDialog.execute();

        verify(dialogUI).askSortOption(anyList());
        verifyNoMoreInteractions(dialogUI);
    }

    @Test
    void testExecute_WithInvalidOption_ShouldShowError() {
        when(dialogUI.askSortOption(anyList())).thenReturn(Optional.of("Invalid Option"));

        sortToysDialog.execute();

        verify(dialogUI).showError("Невірний вибір параметра.");
    }


}
