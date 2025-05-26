package front.ActionGUI;

import back.Toy.Toy;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import back.Toy.ToyTestImpl;
import static org.junit.jupiter.api.Assertions.*;


class FindToyDialogTest {

    @Test
    void testFindByOneParameter() {
        List<Toy> toys = List.of(
                new ToyTestImpl("Пазл", 150),
                new ToyTestImpl("Лялька", 300),
                new ToyTestImpl("Пазл", 200)
        );

        TestableFindToyDialog dialog = new TestableFindToyDialog(toys);

        dialog.enqueueInput("1");               // Кількість параметрів
        dialog.enqueueChoice("1. Назва");       // Пошук по назві
        dialog.enqueueInput("Пазл");            // Значення

        dialog.execute();

        List<List<Toy>> results = dialog.getResults();
        assertEquals(1, results.size());

        List<Toy> filtered = results.get(0);
        assertEquals(2, filtered.size());
        assertTrue(filtered.stream().allMatch(t -> t.getName().equals("Пазл")));
    }

    @Test
    void testFindWithInvalidParamCount() {
        List<Toy> toys = List.of(new ToyTestImpl("Конструктор", 500));

        TestableFindToyDialog dialog = new TestableFindToyDialog(toys);

        dialog.enqueueInput("5");  // Некоректна кількість параметрів

        dialog.execute();

        // Очікуємо, що пошук не виконувався
        assertTrue(dialog.getResults().isEmpty());
    }

    @Test
    void testNoMatchingResults() {
        List<Toy> toys = List.of(new ToyTestImpl("Машинка", 300));

        TestableFindToyDialog dialog = new TestableFindToyDialog(toys);

        dialog.enqueueInput("1");
        dialog.enqueueChoice("1. Назва");
        dialog.enqueueInput("Робот");

        dialog.execute();

        List<List<Toy>> results = dialog.getResults();
        assertEquals(1, results.size());
        assertEquals(0, results.get(0).size());
    }
    @Test
    void testInvalidNumberInput() {
        List<Toy> toys = List.of(new ToyTestImpl("Пазл", 150));
        TestableFindToyDialog dialog = new TestableFindToyDialog(toys);

        dialog.enqueueInput("abc"); // некоректне число

        dialog.execute();

        assertTrue(dialog.getResults().isEmpty()); // не повинен шукати
    }

    @Test
    void testUnknownParameterSkipped() {
        List<Toy> toys = List.of(new ToyTestImpl("Пазл", 150));
        TestableFindToyDialog dialog = new TestableFindToyDialog(toys);

        dialog.enqueueInput("1");
        dialog.enqueueChoice("999. Невідоме");
        dialog.enqueueInput("Щось");

        dialog.execute();

        // Очікуємо, що результат — весь список (нічого не відфільтровано)
        List<List<Toy>> results = dialog.getResults();
        assertEquals(1, results.size());
        assertEquals(1, results.get(0).size());
    }

}
