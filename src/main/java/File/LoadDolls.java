package File;

import Toy.*;
import Log.LoggerUtility;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoadDolls implements ToyLoader {
    private final String fileName = "C:\\Users\\solij\\OneDrive\\Рабочий стол\\Dolls.txt";
    private List<Toy> toys;

    public LoadDolls(List<Toy> toys) {
        this.toys = toys;
    }

    @Override
    public void load() {
        LoggerUtility.logInfo("Початок завантаження ляльок із файлу: " + fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int loadedCount = 0;

            while ((line = br.readLine()) != null) {
                try {
                    String[] data = line.split(",");
                    Doll doll = new Doll(
                            data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                            data[4], Integer.parseInt(data[5]), data[6], data[7],
                            Boolean.parseBoolean(data[8]), Integer.parseInt(data[9]),
                            Integer.parseInt(data[10]), Integer.parseInt(data[11]),
                            data[12], data[13]
                    );
                    toys.add(doll);
                    loadedCount++;
                } catch (Exception ex) {
                    LoggerUtility.logError("Помилка під час обробки рядка: " + line, ex);
                }
            }

            LoggerUtility.logInfo("Ляльки успішно завантажено: " + loadedCount + " записів.");
        } catch (IOException e) {
            LoggerUtility.logError("Помилка зчитування файлу: " + fileName, e);
        }
    }
}
