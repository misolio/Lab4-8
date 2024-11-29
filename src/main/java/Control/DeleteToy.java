package Control;
import Toy.Toy;
import Menu.Command;
import java.util.List;
import java.util.Scanner;

public class DeleteToy implements Command {
    private List<Toy> toys;
    private Scanner scanner;

    public DeleteToy(List<Toy> toys) {
        this.toys = toys;
        this.scanner = new Scanner(System.in);
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (toys.isEmpty()) {
            System.out.println("Список іграшок порожній.");
            return;
        }

        System.out.println("Список іграшок:");
        for (int i = 0; i < toys.size(); i++) {
            System.out.println((i + 1) + ". " + toys.get(i).toString());
        }

        System.out.println("Введіть номер іграшки, яку хочете видалити:");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= toys.size()) {
            Toy removedToy = toys.remove(choice - 1);
            System.out.println("Іграшка \"" + removedToy + "\" була видалена.");
        } else {
            System.out.println("Невірний номер. Спробуйте ще раз.");
        }
    }
}
