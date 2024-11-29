package Control;

import Toy.Toy;
import Menu.Command;
import Sort.*;
import Log.LoggerUtility; // Імпортуємо утиліту для логування

import java.util.*;

public class SortToys implements Command {
    private final Map<Integer, Sorting> sort = new HashMap<>();
    private final List<Toy> toys;
    private Scanner scanner;

    public SortToys(List<Toy> toys) {
        this.toys = toys;
        this.scanner = new Scanner(System.in);
        setupSorting(); // Ініціалізація мапи сортувань
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        LoggerUtility.logInfo("Запуск сортування іграшок.");
        try {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            if (sort.containsKey(choice)) {
                LoggerUtility.logInfo("Обрано параметр сортування: " + choice);
                performSorting(choice);
            } else {
                LoggerUtility.logWarning("Некоректний вибір параметра сортування: " + choice);
                System.out.println("Невірний вибір. Будь ласка, спробуйте знову.");
            }
        } catch (NumberFormatException e) {
            LoggerUtility.logError("Помилка введення: введено нечислове значення.", e);
            System.out.println("Помилка: введіть числове значення.");
        } catch (Exception e) {
            LoggerUtility.logError("Непередбачена помилка під час сортування.", e);
        }
    }

    private void setupSorting() {
        LoggerUtility.logDebug("Ініціалізація мапи сортувань.");
        sort.put(1, new SortByPrice(toys));
        sort.put(2, new SortBySize(toys));
        sort.put(3, new SortByMaterial(toys));
        sort.put(4, new SortByName(toys));
        sort.put(5, new SortByAgeRestrictions(toys));
    }

    private void showMenu() {
        LoggerUtility.logDebug("Виведення меню сортування.");
        System.out.println("Сортуємо за параметром:");
        System.out.println("1. Ціна");
        System.out.println("2. Розмір");
        System.out.println("3. Матеріал");
        System.out.println("4. Ім'я");
        System.out.println("5. Вікові обмеження");
        System.out.print("Оберіть параметр: ");
    }

    private void performSorting(int choice) {
        List<Toy> copyOfToys = new ArrayList<>(toys);
        Sorting selectedSorting = sort.get(choice);

        LoggerUtility.logInfo("Запуск сортування за параметром " + choice);
        selectedSorting.sort();
        LoggerUtility.logInfo("Сортування завершено.");

        System.out.println("Відсортовані іграшки:");
        copyOfToys.forEach(System.out::println);

        toys.clear();
        toys.addAll(copyOfToys);
        LoggerUtility.logInfo("Оновлено список іграшок після сортування.");
    }
}
