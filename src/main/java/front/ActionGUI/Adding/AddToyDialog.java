package front.ActionGUI.Adding;

import back.Toy.Toy;
import front.ActionGUI.Command;
import back.Log.LoggerUtility;

import java.sql.Connection;
import java.util.List;

public class AddToyDialog implements Command {
    final AddToyService service;

    public AddToyDialog(List<Toy> toys, int budget, Connection conn) {
        LoggerUtility.logInfo("Ініціалізація AddToyDialog з бюджетом: " + budget);
        this.service = new AddToyService(toys, budget, conn, new AddToyService.ToyDialogFXView());
    }

    public AddToyDialog(AddToyService service) {
        LoggerUtility.logInfo("Ініціалізація AddToyDialog для тестування.");
        this.service = service;
    }

    @Override
    public void execute() {
        LoggerUtility.logInfo("Виконання команди додавання іграшки через AddToyDialog...");
        service.run();
        LoggerUtility.logInfo("✔ Додавання іграшки завершено.");
    }

    public int getBudget() {
        LoggerUtility.logInfo("Отримання поточного бюджету: " + service.getBudget());
        return service.getBudget();
    }
}
