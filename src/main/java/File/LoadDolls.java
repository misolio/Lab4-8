package File;

import Toy.Doll;
import Toy.Toy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public  class LoadDolls implements ToyLoader {
    private final List<Toy> toys;
    private final String fileName = "C:\\Users\\solij\\OneDrive\\Рабочий стол\\Dolls.txt";

    public LoadDolls(List<Toy> toys) {
        this.toys = toys;
    }

    @Override
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Doll doll = new Doll(
                        data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), data[4], Integer.parseInt(data[5]),
                        data[6], data[7], Boolean.parseBoolean(data[8]), Integer.parseInt(data[9]), Integer.parseInt(data[10]),
                        Integer.parseInt(data[11]), data[12], data[13]);
                toys.add(doll);
            }
            System.out.println("Ляльки завантажено з " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка зчитування файлу " + fileName);
        }
    }

}
