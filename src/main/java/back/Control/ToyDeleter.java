package back.Control;

import back.Toy.Toy;
import back.Log.LoggerUtility;

import java.util.List;

public class ToyDeleter {
    private final List<Toy> toys;

    public ToyDeleter(List<Toy> toys) {
        this.toys = toys;
        LoggerUtility.logInfo("ToyDeleter ініціалізовано. Кількість іграшок: " + toys.size());
    }

    public boolean contains(Toy toy) {
        boolean result = toys.contains(toy);
        LoggerUtility.logDebug("Перевірка наявності іграшки \"" + toy.getName() + "\": " + result);
        return result;
    }

    public boolean delete(Toy toy) {
        if (toys.remove(toy)) {
            LoggerUtility.logInfo("✔ Іграшку \"" + toy.getName() + "\" успішно видалено.");
            return true;
        } else {
            LoggerUtility.logWarning("✖ Не вдалося видалити іграшку \"" + toy.getName() + "\" — її не знайдено.");
            return false;
        }
    }
}
