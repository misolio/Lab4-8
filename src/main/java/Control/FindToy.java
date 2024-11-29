package Control;

import Toy.Toy;
import Menu.Command;
import Filter.*;
import Log.LoggerUtility;

import java.util.*;
import java.util.stream.Collectors;

public class FindToy implements Command {
    private List<Toy> toys;
    private List<Toy> filteredToys;
    private final Map<Integer, Filtering> filters = new HashMap<>();
    private Scanner scanner;

    public FindToy(List<Toy> toys) {
        this.toys = toys;
        this.filteredToys = new ArrayList<>(toys);
        filters.put(1, new FilterByName(toys));
        filters.put(2, new FilterByPrice(toys));
        filters.put(3, new FilterByMaterial(toys));
        filters.put(4, new FilterBySize(toys));
        filters.put(5, new FilterByAgeRestrictions(toys));
        this.scanner = new Scanner(System.in);
        LoggerUtility.logInfo("FindToy клас ініціалізовано.");
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
        LoggerUtility.logDebug("Сканер для FindToy оновлено.");
    }

    public List<Toy> getFilteredToys() {
        return filteredToys;
    }

    @Override
    public void execute() {
        LoggerUtility.logInfo("Розпочато пошук іграшок.");
        System.out.println("Шукаємо іграшку за певними параметрами");

        try {
            System.out.println("Введіть кількість параметрів для пошуку (1-3): ");
            int numOfParams = scanner.nextInt();
            scanner.nextLine();

            if (numOfParams < 1 || numOfParams > 3) {
                LoggerUtility.logWarning("Некоректна кількість параметрів: " + numOfParams);
                System.out.println("Помилка: кількість параметрів повинна бути від 1 до 3.");
                return;
            }

            LoggerUtility.logInfo("Кількість параметрів для пошуку: " + numOfParams);

            filteredToys = new ArrayList<>(toys);

            for (int i = 0; i < numOfParams; i++) {
                System.out.println("""
                Оберіть параметр для пошуку:
                1. Назва
                2. Ціна (менша введеної)
                3. Матеріал
                4. Розмір
                5. Вікові обмеження (менші введеного)
                """);

                int paramChoice = scanner.nextInt();
                scanner.nextLine();

                if (!filters.containsKey(paramChoice)) {
                    LoggerUtility.logWarning("Некоректний вибір параметра: " + paramChoice);
                    System.out.println("Невірний вибір параметра. Спробуйте знову.");
                    continue;
                }

                System.out.print("Введіть значення для пошуку: ");
                String value = scanner.nextLine();

                LoggerUtility.logInfo("Обрано параметр: " + paramChoice + ", значення: " + value);

                Filtering selectedFilter = filters.get(paramChoice);
                if (selectedFilter != null) {
                    filteredToys = selectedFilter.filter(value).stream()
                            .filter(filteredToys::contains)
                            .collect(Collectors.toList());
                    LoggerUtility.logDebug("Фільтрування завершено. Кількість результатів: " + filteredToys.size());
                }
            }

            LoggerUtility.logInfo("Пошук завершено. Кількість знайдених іграшок: " + filteredToys.size());
            System.out.println("Знайдені іграшки:");
            filteredToys.forEach(System.out::println);

        } catch (InputMismatchException e) {
            LoggerUtility.logError("Помилка вводу під час пошуку іграшок.", e);
            System.out.println("Помилка: введіть правильне значення.");
            scanner.nextLine(); // Очищення вводу
        } catch (Exception e) {
            LoggerUtility.logError("Непередбачена помилка під час пошуку іграшок.", e);
        }
    }
}
