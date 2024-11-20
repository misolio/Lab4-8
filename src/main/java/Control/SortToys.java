package Control;

import Toy.Toy;
import Menu.Command;
import Sort.*;

import java.util.*;

public class SortToys implements Command {
    private final Map<Integer, Sorting> sort = new HashMap<>();
    private final List<Toy> toys;
    private Scanner scanner;

    public SortToys(List<Toy> toys) {
        this.toys = toys;
        this.scanner = new Scanner(System.in);
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        Menu();
        int choice = scanner.nextInt();

        List<Toy> copyOfToys = new ArrayList<>(toys);
        setupSorting(copyOfToys);

        Sorting selectedSorting = sort.get(choice);
        if (selectedSorting != null) {
            selectedSorting.sort();
            System.out.println("Відсортовані іграшки:");
            copyOfToys.forEach(System.out::println);
            toys.clear();
            toys.addAll(copyOfToys);
        } else {
            System.out.println("Невірний вибір.");
        }
    }

    public void setupSorting(List<Toy> copyOfToys) {
        sort.clear();
        sort.put(1, new SortByPrice(copyOfToys));
        sort.put(2, new SortBySize(copyOfToys));
        sort.put(3, new SortByMaterial(copyOfToys));
        sort.put(4, new SortByName(copyOfToys));
        sort.put(5, new SortByAgeRestrictions(copyOfToys));
    }

    public void Menu() {
        System.out.println("Сортуємо за параметром:");
        System.out.println("1. Ціна");
        System.out.println("2. Розмір");
        System.out.println("3. Матеріал");
        System.out.println("4. Ім'я");
        System.out.println("5. Вікові обмеження");
    }
}
