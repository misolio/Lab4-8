package back.Loader;

import back.Toy.PlushToy;
import back.Toy.Toy;
import back.Log.LoggerUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlushToyLoader implements Loading {

    private final Connection conn;

    public PlushToyLoader(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Toy getToyById(int toyId) {
        LoggerUtility.logInfo("Завантаження м’якої іграшки за ID: " + toyId);
        List<Toy> result = load(toyId);
        if (result.isEmpty()) {
            LoggerUtility.logWarning("Не знайдено м’якої іграшки з ID: " + toyId);
            return null;
        }
        return result.get(0);
    }

    @Override
    public List<Toy> load(int toyId) {
        List<Toy> list = new ArrayList<>();
        try {
            LoggerUtility.logDebug("Виконання SELECT з toy і plush_toy для ID: " + toyId);

            String toySql = "SELECT * FROM toy WHERE toy_id = ?";
            PreparedStatement toyStmt = conn.prepareStatement(toySql);
            toyStmt.setInt(1, toyId);
            ResultSet toyRs = toyStmt.executeQuery();

            if (toyRs.next()) {
                String plushSql = "SELECT * FROM plush_toy WHERE plush_toy_id = ?";
                PreparedStatement plushStmt = conn.prepareStatement(plushSql);
                plushStmt.setInt(1, toyId);
                ResultSet plushRs = plushStmt.executeQuery();

                if (plushRs.next()) {
                    PlushToy toy = new PlushToy(
                            toyRs.getString("name"),
                            toyRs.getString("type"),
                            toyRs.getInt("price"),
                            toyRs.getInt("size"),
                            toyRs.getString("material"),
                            toyRs.getInt("age_restrictions"),
                            plushRs.getBoolean("presence_of_sound_effects"),
                            plushRs.getInt("height"),
                            plushRs.getInt("lengthh"),
                            plushRs.getInt("width")
                    );
                    list.add(toy);
                    LoggerUtility.logInfo("✔ Завантажено м’яку іграшку: " + toy.getName());
                } else {
                    LoggerUtility.logWarning("✖ Не знайдено запису у plush_toy з ID: " + toyId);
                }
            } else {
                LoggerUtility.logWarning("✖ Не знайдено запису у toy з ID: " + toyId);
            }
        } catch (SQLException e) {
            LoggerUtility.logError("Помилка під час завантаження м’якої іграшки з ID: " + toyId, e);
        }
        return list;
    }

    @Override
    public List<Toy> loadAll() {
        List<Toy> list = new ArrayList<>();
        try {
            LoggerUtility.logInfo("Завантаження всіх м’яких іграшок...");

            String sql = """
                SELECT t.*, p.* FROM toy t
                JOIN plush_toy p ON t.toy_id = p.plush_toy_id
            """;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PlushToy toy = new PlushToy(
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("price"),
                        rs.getInt("size"),
                        rs.getString("material"),
                        rs.getInt("age_restrictions"),
                        rs.getBoolean("presence_of_sound_effects"),
                        rs.getInt("height"),
                        rs.getInt("lengthh"),
                        rs.getInt("width")
                );
                list.add(toy);
            }

            LoggerUtility.logInfo("✔ Завантажено " + list.size() + " м’яких іграшок.");

        } catch (SQLException e) {
            LoggerUtility.logError("Помилка при завантаженні всіх м’яких іграшок", e);
        }
        return list;
    }
}
