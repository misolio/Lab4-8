package File;

import Toy.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoadTransport implements ToyLoader{
    private final String fileName= "C:\\Users\\solij\\OneDrive\\Рабочий стол\\Transport.txt";;
    private  List<Toy> toys;

    public LoadTransport(List<Toy> toys) {
        this.toys = toys;
    }

    @Override
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Transport transport = new Transport(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                        data[4], Integer.parseInt(data[5]), data[6], data[7], Integer.parseInt(data[8]),
                        Integer.parseInt(data[9]), Integer.parseInt(data[10]), Integer.parseInt(data[11]),
                        Boolean.parseBoolean(data[12]));
                toys.add(transport);
            }
            System.out.println("Транспорт завантажено з " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка зчитування файлу " + fileName);
        }
    }
}
