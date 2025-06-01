package front.ActionGUI.Finder;

import java.util.List;
import java.util.Optional;

public interface ToySearchDialogUI {
    Optional<Integer> askNumberOfParams();
    Optional<String> chooseParamName(List<String> availableParams, int index);
    Optional<String> askParamValue(String paramName);
    void showError(String message);
}
