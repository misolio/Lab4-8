package Control;
import Toy.Toy;
import Menu.Command;
import Filter.*;
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
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<Toy> getFilteredToys() {
        return filteredToys;
    }

    @Override
    public void execute() {
        System.out.println("Шукаємо іграшку за певними параметрами");

        System.out.println("Введіть кількість параметрів для пошуку (1-3): ");
        int numOfParams = scanner.nextInt();
        scanner.nextLine();

        filteredToys = new ArrayList<>(toys);

        for (int i = 0; i < numOfParams; i++) {
            System.out.println("""
            Оберіть параметр для пошуку:
            1. Назва
            2. Ціна(менша введеної)
            3. Матеріал
            4. Розмір
            5. Вікові обмеження(менші введеного)
            """);

            int paramChoice = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Введіть значення для пошуку: ");
            String value = scanner.nextLine();

            Filtering selectedFilter = filters.get(paramChoice);
            if (selectedFilter != null) {
                filteredToys = selectedFilter.filter(value).stream()
                        .filter(filteredToys::contains)
                        .collect(Collectors.toList());
            } else {
                System.out.println("Невірний вибір параметра.");
            }
        }

        System.out.println("Знайдені іграшки:");
        filteredToys.forEach(System.out::println);
    }
}
