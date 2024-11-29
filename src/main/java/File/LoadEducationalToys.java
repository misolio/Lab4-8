package File;

import Toy.*;
import Log.LoggerUtility;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoadEducationalToys implements ToyLoader {
    private final String fileName = "C:\\Users\\solij\\OneDrive\\Рабочий стол\\EducationalGames.txt";
    private List<Toy> toys;

    public LoadEducationalToys(List<Toy> toys) {
        this.toys = toys;
    }

    @Override
    public void load() {
        LoggerUtility.logInfo("Початок завантаження освітніх іграшок із файлу: " + fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int loadedCount = 0;

            while ((line = br.readLine()) != null) {
                try {
                    String[] data = line.split(",");
                    EducationalToy eduToy = new EducationalToy(
                            data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                            data[4], Integer.parseInt(data[5]), data[6], data[7], Integer.parseInt(data[8])
                    );
                    toys.add(eduToy);
                    loadedCount++;
                } catch (Exception ex) {
                    LoggerUtility.logError("Помилка під час обробки рядка: " + line, ex);
                }
            }

            LoggerUtility.logInfo("Освітні іграшки успішно завантажено: " + loadedCount + " записів.");
        } catch (IOException e) {
            LoggerUtility.logError("Помилка зчитування файлу: " + fileName, e);
        }
    }
}
