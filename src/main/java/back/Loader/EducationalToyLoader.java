package back.Loader;

import back.Toy.EducationalToy;
import back.Toy.Toy;
import back.Log.LoggerUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EducationalToyLoader implements Loading {

    private final Connection conn;

    public EducationalToyLoader(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Toy getToyById(int toyId) {
        LoggerUtility.logInfo("Завантаження розвиваючої іграшки за ID: " + toyId);
        List<Toy> result = load(toyId);
        if (result.isEmpty()) {
            LoggerUtility.logWarning("Не знайдено розвиваючої іграшки з ID: " + toyId);
            return null;
        }
        return result.get(0);
    }

    @Override
    public List<Toy> load(int toyId) {
        List<Toy> list = new ArrayList<>();
        try {
            LoggerUtility.logDebug("Запит до таблиці toy та educational_toy з ID: " + toyId);

            String toySql = "SELECT * FROM toy WHERE toy_id = ?";
            PreparedStatement toyStmt = conn.prepareStatement(toySql);
            toyStmt.setInt(1, toyId);
            ResultSet toyRs = toyStmt.executeQuery();

            if (toyRs.next()) {
                String educationalToySql = "SELECT * FROM educational_toy WHERE educational_toy_id = ?";
                PreparedStatement educationalToyStmt = conn.prepareStatement(educationalToySql);
                educationalToyStmt.setInt(1, toyId);
                ResultSet educationalToyRs = educationalToyStmt.executeQuery();

                if (educationalToyRs.next()) {
                    EducationalToy toy = new EducationalToy(
                            toyRs.getString("name"),
                            toyRs.getString("type"),
                            toyRs.getInt("price"),
                            toyRs.getInt("size"),
                            toyRs.getString("material"),
                            toyRs.getInt("age_restrictions"),
                            educationalToyRs.getInt("number_of_details")
                    );
                    list.add(toy);
                    LoggerUtility.logInfo("✔ Завантажено розвиваючу іграшку: " + toy.getName());
                } else {
                    LoggerUtility.logWarning("✖ Не знайдено запису у таблиці educational_toy з ID: " + toyId);
                }
            } else {
                LoggerUtility.logWarning("✖ Не знайдено запису у таблиці toy з ID: " + toyId);
            }
        } catch (SQLException e) {
            LoggerUtility.logError("Помилка під час завантаження розвиваючої іграшки з ID: " + toyId, e);
        }
        return list;
    }

    @Override
    public List<Toy> loadAll() {
        List<Toy> list = new ArrayList<>();
        try {
            LoggerUtility.logInfo("Завантаження всіх розвиваючих іграшок...");

            String sql = """
                SELECT t.*, p.* FROM toy t
                JOIN educational_toy p ON t.toy_id = p.educational_toy_id
            """;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EducationalToy toy = new EducationalToy(
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("price"),
                        rs.getInt("size"),
                        rs.getString("material"),
                        rs.getInt("age_restrictions"),
                        rs.getInt("number_of_details")
                );
                list.add(toy);
            }

            LoggerUtility.logInfo("✔ Завантажено " + list.size() + " розвиваючих іграшок.");

        } catch (SQLException e) {
            LoggerUtility.logError("Помилка під час завантаження всіх розвиваючих іграшок", e);
        }
        return list;
    }
}
