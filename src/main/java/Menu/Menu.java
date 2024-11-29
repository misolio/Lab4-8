package Menu;

import Control.AddToy;
import Control.DeleteToy;
import Control.FindToy;
import Control.SortToys;
import Toy.Toy;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Menu {
    protected Map<Integer, Command> menuItems;
    private Scanner scanner;

    public Menu(List<Toy> toys) {
        scanner = new Scanner(System.in);
        System.out.println("Введіть бюджет:");
        int money = scanner.nextInt();
        menuItems = new LinkedHashMap<>();
        menuItems.put(1, new AddToy(toys, money));
        menuItems.put(2, new SortToys(toys));
        menuItems.put(3, new FindToy(toys));
        menuItems.put(4, new DeleteToy(toys));
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        System.out.println("""
        \nОберіть команду:
        1. Додати іграшку до кімнати
        2. Сортувати іграшки за параметром
        3. Знайти іграшку за параметром
        4. Викинути іграшку
        5. Вихід з програми
        """);
    }

    public void executeCommand(Integer command) {
        menuItems.getOrDefault(command, () -> {
            System.out.println("Вихід.");
        }).execute();
    }


}
