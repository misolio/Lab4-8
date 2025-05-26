package front.ActionGUI;

import back.Control.ToyAdder;
import back.Loader.Loading;
import back.Toy.Toy;
import back.Toy.ToyTestImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.Connection;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AddToyDialogTest {

    @Mock
    private ToyAdder toyAdder;

    @Mock
    private Loading boardGameLoader;

    private AddToyDialog dialog;
    private List<Toy> mockToys;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockToys = List.of(
                new ToyTestImpl("Настільна гра 1", 200),
                new ToyTestImpl("Настільна гра 2", 300)
        );

        Connection conn = mock(Connection.class);
        dialog = new AddToyDialog(new ArrayList<>(), 1000, conn);
    }

    @Test
    void testBudgetReductionAfterAdd() {
        Toy toy = new ToyTestImpl("Test Toy", 500);
        ToyAdder adder = new ToyAdder(new ArrayList<>(), 600);
        boolean added = adder.addToyIfAffordable(toy);

        assertTrue(added);
        assertEquals(100, adder.getBudget());
    }

    @Test
    void testAddToyIfAffordableFails() {
        Toy toy = new ToyTestImpl("Test Toy", 700);
        ToyAdder adder = new ToyAdder(new ArrayList<>(), 600);
        boolean added = adder.addToyIfAffordable(toy);

        assertFalse(added);
        assertEquals(600, adder.getBudget());
    }
    @Test
    void testExecuteAddsOnlyAffordableToys() {
        List<Toy> toyList = new ArrayList<>();
        Connection conn = mock(Connection.class);

        Toy affordable = new ToyTestImpl("Іграшка 1", 300);
        Toy expensive = new ToyTestImpl("Іграшка 2", 800);

        TestableAddToyDialog dialog = new TestableAddToyDialog(toyList, 500, conn);

        // Мокаємо loaders вручну
        Loading mockedLoader = mock(Loading.class);
        when(mockedLoader.loadAll()).thenReturn(List.of(affordable, expensive));

        dialog.enqueueType(1); // тип 1
        dialog.enqueueSelection(List.of(affordable, expensive));
        dialog.enqueueConfirmation(false); // завершення

        // підставляємо наш мок
        dialog.loaders.put(1, mockedLoader);

        dialog.execute();

        // лише affordable іграшка має додатися
        assertEquals(1, toyList.size());
        assertEquals("Іграшка 1", toyList.get(0).getName());
        assertEquals(200, dialog.getBudget());
    }
    @Test
    void testExecuteCoversProtectedMethods() {
        List<Toy> addedToys = new ArrayList<>();
        Connection conn = mock(Connection.class);

        Toy toy1 = new ToyTestImpl("Дешева", 200);
        Toy toy2 = new ToyTestImpl("Дорога", 900);

        TestableAddToyDialog dialog = new TestableAddToyDialog(addedToys, 1000, conn);

        // Імітуємо: вибір типу → список іграшок → підтвердження завершити
        dialog.enqueueType(1);
        dialog.enqueueSelection(List.of(toy1, toy2));
        dialog.enqueueConfirmation(false); // завершити цикл

        // Підставляємо мок для loaders
        Loading loaderMock = mock(Loading.class);
        when(loaderMock.loadAll()).thenReturn(List.of(toy1, toy2));
        dialog.loaders.put(1, loaderMock);

        dialog.execute();

        // Перевіряємо, що додалась лише одна (toy1)
        assertEquals(1, addedToys.size());
        assertEquals("Дешева", addedToys.get(0).getName());

        // Перевіряємо що alert був викликаний і містить відповідь
        List<String> alerts = dialog.getAlertMessages();
        assertEquals(1, alerts.size());
        assertTrue(alerts.get(0).contains("✔ Дешева"));
        assertTrue(alerts.get(0).contains("✖ Недостатньо бюджету для Дорога"));
    }


}
