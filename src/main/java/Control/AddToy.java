package Control;
import Toy.*;
import Menu.Command;
import java.util.*;
import File.*;

public class AddToy implements Command {
    private List<Toy> toys;
    public Map<Integer, ToyLoader> loaders = new HashMap<>();
    private int budget;
    private Scanner scanner;

    public AddToy(List<Toy> toys, int budget) {
        this.toys = toys;
        this.budget = budget;
        loaders.put(1, new LoadBoardGames(toys));
        loaders.put(2, new LoadDolls(toys));
        loaders.put(3, new LoadTransport(toys));
        loaders.put(4, new LoadPlushToy(toys));
        loaders.put(5, new LoadEducationalToys(toys));
    }
    public void setScanner(Scanner scanner) { this.scanner = scanner;}

    public int getBudget(){
        return budget;
    }
    @Override
    public void execute() {
        System.out.println("Додаємо іграшки з файлів...");
        selectAndLoadToys();
        System.out.println("Іграшки додано:");
        for (Toy toy : toys) {
            System.out.println(toy);
        }
        System.out.println("Залишок бюджету: " + budget);
    }

    public void selectAndLoadToys() {
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
                System.out.println("Завантаження завершено.");
                break;
            }
            addToysWithinBudget(choice);
        }
    }

    public void addToysWithinBudget(int choice) {
        ToyLoader loader = loaders.get(choice);
        if (loader != null) {
            int initialSize = toys.size();
            loader.load();

            for (int i = initialSize; i < toys.size(); i++) {
                Toy toy = toys.get(i);
                if (!canAddToy(toy)) {
                    System.out.println("Іграшка " + toy.getName() + " не додана через перевищення бюджету.");
                    toys.remove(i);
                    i--;
                }
            }
        } else {
            System.out.println("Невірний вибір, спробуйте ще раз.");
        }
    }
    public boolean canAddToy(Toy toy) {
        if (toy.getPrice() <= budget) {
            budget -= toy.getPrice();
            System.out.println("Іграшка " + toy.getName() + " додана. Ціна : "+toy.getPrice()+" Залишок бюджету: " + budget);
            return true;
        } else {
            return false;
        }
    }
}
