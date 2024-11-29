package File;

import Log.LoggerUtility;
import Toy.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoadPlushToy implements ToyLoader {
    private List<Toy> toys;
    private final String fileName = "C:\\Users\\solij\\OneDrive\\Рабочий стол\\PlushToys.txt";

    public LoadPlushToy(List<Toy> toys) {
        this.toys = toys;
    }

    @Override
    public void load() {
        LoggerUtility.logInfo("Початок завантаження м'яких іграшок із файлу: " + fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int loadedCount = 0;

            while ((line = br.readLine()) != null) {
                try {
                    String[] data = line.split(",");
                    PlushToy plushToy = new PlushToy(
                            data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                            data[4], Integer.parseInt(data[5]), data[6], data[7],
                            Boolean.parseBoolean(data[8]), Integer.parseInt(data[9]),
                            Integer.parseInt(data[10]), Integer.parseInt(data[11])
                    );
                    toys.add(plushToy);
                    loadedCount++;
                } catch (Exception ex) {
                    LoggerUtility.logError("Помилка під час обробки рядка: " + line, ex);
                }
            }

            LoggerUtility.logInfo("М'які іграшки успішно завантажено: " + loadedCount + " записів.");
        } catch (IOException e) {
            LoggerUtility.logError("Помилка зчитування файлу: " + fileName, e);
        }
    }
}
