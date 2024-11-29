package Control;

import Toy.*;
import Menu.Command;
import File.*;
import Log.LoggerUtility;

import java.util.*;

public class AddToy implements Command {
    private List<Toy> toys;
    public Map<Integer, ToyLoader> loaders = new HashMap<>();
    private int budget;
    private Scanner scanner;

    public AddToy(List<Toy> toys, int budget) {
        this.toys = toys;
        this.budget = budget;

        // Ініціалізація типів завантажувачів
        loaders.put(1, new LoadBoardGames(toys));
        loaders.put(2, new LoadDolls(toys));
        loaders.put(3, new LoadTransport(toys));
        loaders.put(4, new LoadPlushToy(toys));
        loaders.put(5, new LoadEducationalToys(toys));

        LoggerUtility.logInfo("AddToy клас ініціалізовано з бюджетом: " + budget);
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
        LoggerUtility.logDebug("Сканер для AddToy оновлено.");
    }

    public int getBudget() {
        return budget;
    }

    @Override
    public void execute() {
        LoggerUtility.logInfo("Розпочато додавання іграшок з файлів.");
        System.out.println("Додаємо іграшки з файлів...");

        selectAndLoadToys();

        LoggerUtility.logInfo("Іграшки додано. Загальна кількість: " + toys.size());
        System.out.println("Іграшки додано:");
        for (Toy toy : toys) {
            System.out.println(toy);
        }
        System.out.println("Залишок бюджету: " + budget);
        LoggerUtility.logInfo("Залишок бюджету: " + budget);
    }

    public void selectAndLoadToys() {
        LoggerUtility.logDebug("Початок вибору типів іграшок для завантаження.");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                \nВиберіть тип іграшок для завантаження:
                1 - Настільні ігри
                2 - Ляльки
                3 - Транспорт
                4 - М'які іграшки
                5 - Освітні іграшки
                0 - Завершити вибір
                """);
            int choice = scanner.nextInt();

            if (choice == 0) {
                LoggerUtility.logInfo("Користувач завершив вибір завантаження іграшок.");
                System.out.println("Завантаження завершено.");
                break;
            }

            LoggerUtility.logInfo("Обрано тип іграшок для завантаження: " + choice);
            addToysWithinBudget(choice);
        }
    }

    public void addToysWithinBudget(int choice) {
        ToyLoader loader = loaders.get(choice);

        if (loader != null) {
            int initialSize = toys.size();
            LoggerUtility.logInfo("Завантаження іграшок для типу: " + choice);

            loader.load(); // Завантаження іграшок

            for (int i = initialSize; i < toys.size(); i++) {
                Toy toy = toys.get(i);
                if (!canAddToy(toy)) {
                    LoggerUtility.logWarning("Іграшка " + toy.getName() + " не додана через перевищення бюджету.");
                    System.out.println("Іграшка " + toy.getName() + " не додана через перевищення бюджету.");
                    toys.remove(i);
                    i--;
                }
            }

            LoggerUtility.logInfo("Завантаження завершено. Додано: " + (toys.size() - initialSize) + " іграшок.");
        } else {
            LoggerUtility.logWarning("Невірний вибір типу іграшок: " + choice);
            System.out.println("Невірний вибір, спробуйте ще раз.");
        }
    }

    public boolean canAddToy(Toy toy) {
        if (toy.getPrice() <= budget) {
            budget -= toy.getPrice();
            LoggerUtility.logInfo("Іграшка " + toy.getName() + " додана. Ціна: " + toy.getPrice() + ", залишок бюджету: " + budget);
            System.out.println("Іграшка " + toy.getName() + " додана. Ціна: " + toy.getPrice() + ", залишок бюджету: " + budget);
            return true;
        } else {
            LoggerUtility.logWarning("Іграшка " + toy.getName() + " не може бути додана через перевищення бюджету.");
            return false;
        }
    }
}
