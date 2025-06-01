package front.ActionGUI.Adding;

public class TestableToyDialogFXView extends AddToyService.ToyDialogFXView {
    private String simulatedChoice = null;

    public void setSimulatedChoice(String choice) {
        this.simulatedChoice = choice;
    }

    @Override
    public int promptToyType() {
        if (simulatedChoice != null) {
            return switch (simulatedChoice) {
                case "Настільні ігри" -> 1;
                case "Ляльки" -> 2;
                case "Транспорт" -> 3;
                case "М’які іграшки" -> 4;
                case "Освітні іграшки" -> 5;
                default -> -1;
            };
        }
        return super.promptToyType(); // виклик звичайного діалогу
    }
}
