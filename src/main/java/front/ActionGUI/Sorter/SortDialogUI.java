package front.ActionGUI;

import java.util.List;
import java.util.Optional;

public interface SortDialogUI {
    Optional<String> askSortOption(List<String> availableOptions);
    void showError(String message);
}
