package File;

import Toy.BoardGames;
import Toy.Toy;
import Log.LoggerUtility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoadBoardGames implements ToyLoader {
    private final String fileName = "C:\\Users\\solij\\OneDrive\\Рабочий стол\\BoardGame.txt";
    private final List<Toy> toys;

    public LoadBoardGames(List<Toy> toys) {
        this.toys = toys;
    }

    @Override
    public void load() {
        LoggerUtility.logInfo("Початок завантаження настільних ігор із файлу: " + fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int loadedCount = 0;

            while ((line = br.readLine()) != null) {
                try {
                    String[] data = line.split(",");
                    if (data.length != 11) {
                        LoggerUtility.logWarning("Неправильний формат у рядку: " + line);
                        continue;
                    }

                    BoardGames game = new BoardGames(
                            data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                            data[4], Integer.parseInt(data[5]), data[6], data[7],
                            Integer.parseInt(data[8]), Integer.parseInt(data[9]), Integer.parseInt(data[10])
                    );
                    toys.add(game);
                    loadedCount++;
                } catch (Exception ex) {
                    LoggerUtility.logError("Помилка під час обробки рядка: " + line, ex);
                }
            }

            LoggerUtility.logInfo("Настільні ігри успішно завантажено: " + loadedCount + " записів.");
        } catch (IOException e) {
            LoggerUtility.logError("Помилка зчитування файлу: " + fileName, e);
        }
    }
}
