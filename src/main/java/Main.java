import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Menu.Menu;
import Toy.Toy;

public class Main {
    public static void main(String[] args) {
        List<Toy> toys = new ArrayList<>();
        Menu menu = new Menu(toys);
        runProgram(menu, new Scanner(System.in));
    }

    public static void runProgram(Menu menu, Scanner input) {
        int command;
        do {
            menu.displayMenu();
            System.out.print("Виберіть команду: ");
            command = input.nextInt();
            System.out.println();

            if (command >= 1 && command <= 5) {
                menu.executeCommand(command);
            } else {
                System.out.println("Невірна команда. Спробуйте ще раз.");
            }
        } while (command != 5);

        System.out.println("Програма завершена.");
    }
}
