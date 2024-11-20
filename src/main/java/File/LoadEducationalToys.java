package File;

import Toy.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoadEducationalToys  implements ToyLoader{
    private final String fileName = "C:\\Users\\solij\\OneDrive\\Рабочий стол\\EducationalGames.txt" ;
    private  List<Toy> toys;

    public LoadEducationalToys(List<Toy> toys) {
        this.toys= toys;
    }

    @Override
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                EducationalToy eduToy = new EducationalToy(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                        data[4], Integer.parseInt(data[5]), data[6], data[7], Integer.parseInt(data[8]));
                toys.add(eduToy);
            }
            System.out.println("Освітні іграшки завантажено з " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка зчитування файлу " + fileName);
        }
    }
}
