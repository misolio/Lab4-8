package front.ActionGUI;

import back.Toy.Toy;

import javafx.scene.control.*;



import java.sql.Connection;
import java.util.*;

public class TestableAddToyDialog extends AddToyDialog {
    private final Queue<Integer> typeQueue = new LinkedList<>();
    private final Queue<List<Toy>> selectionQueue = new LinkedList<>();
    private final Queue<Boolean> confirmQueue = new LinkedList<>();
    private final List<String> alertMessages = new ArrayList<>();

    public TestableAddToyDialog(List<Toy> toys, int budget, Connection conn) {
        super(toys, budget, conn);
    }

    public void enqueueType(int type) {
        typeQueue.add(type);
    }

    public void enqueueSelection(List<Toy> toys) {
        selectionQueue.add(toys);
    }

    public void enqueueConfirmation(boolean confirm) {
        confirmQueue.add(confirm);
    }

    public List<String> getAlertMessages() {
        return alertMessages;
    }

    @Override
    protected int promptToyType() {
        return typeQueue.poll();
    }

    @Override
    protected List<Toy> promptToySelection(List<Toy> availableToys) {
        return selectionQueue.poll();
    }

    @Override
    protected boolean confirm(String message) {
        return confirmQueue.poll();
    }

    @Override
    protected void showAlert(Alert.AlertType type, String message) {
        alertMessages.add(message); // лог для перевірки у тестах
    }
}
