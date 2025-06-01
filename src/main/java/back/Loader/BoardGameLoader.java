package back.Loader;

import back.Toy.BoardGames;
import back.Toy.Toy;
import back.Log.LoggerUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardGameLoader implements Loading {
    private final Connection conn;

    public BoardGameLoader(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Toy getToyById(int toyId) {
        LoggerUtility.logInfo("Завантаження настільної гри за ID: " + toyId);
        List<Toy> result = load(toyId);
        if (result.isEmpty()) {
            LoggerUtility.logWarning("Не знайдено настільної гри з ID: " + toyId);
            return null;
        }
        return result.get(0);
    }

    @Override
    public List<Toy> load(int toyId) {
        List<Toy> list = new ArrayList<>();
        try {
            LoggerUtility.logDebug("Виконується запит до таблиці toy та board_game з ID: " + toyId);

            String toySql = "SELECT * FROM toy WHERE toy_id = ?";
            PreparedStatement toyStmt = conn.prepareStatement(toySql);
            toyStmt.setInt(1, toyId);
            ResultSet toyRs = toyStmt.executeQuery();

            if (toyRs.next()) {
                String boardGameSql = "SELECT * FROM board_game WHERE board_game_id = ?";
                PreparedStatement boardGameStmt = conn.prepareStatement(boardGameSql);
                boardGameStmt.setInt(1, toyId);
                ResultSet boardGameRs = boardGameStmt.executeQuery();

                if (boardGameRs.next()) {
                    BoardGames toy = new BoardGames(
                            toyRs.getString("name"),
                            toyRs.getString("type"),
                            toyRs.getInt("price"),
                            toyRs.getInt("size"),
                            toyRs.getString("material"),
                            toyRs.getInt("age_restrictions"),
                            boardGameRs.getInt("min_number_of_players"),
                            boardGameRs.getInt("max_number_of_players"),
                            boardGameRs.getInt("playing_time")
                    );
                    list.add(toy);
                    LoggerUtility.logInfo("✔ Завантажено настільну гру: " + toy.getName());
                } else {
                    LoggerUtility.logWarning("✖ Не знайдено запису у таблиці board_game з ID: " + toyId);
                }
            } else {
                LoggerUtility.logWarning("✖ Не знайдено запису у таблиці toy з ID: " + toyId);
            }

        } catch (SQLException e) {
            LoggerUtility.logError("Помилка під час завантаження настільної гри за ID: " + toyId, e);
        }
        return list;
    }

    @Override
    public List<Toy> loadAll() {
        List<Toy> list = new ArrayList<>();
        try {
            LoggerUtility.logInfo("Завантаження всіх настільних ігор...");
            String sql = """
                SELECT t.*, p.* FROM toy t
                JOIN board_games p ON t.toy_id = p.board_game_id
            """;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BoardGames toy = new BoardGames(
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("price"),
                        rs.getInt("size"),
                        rs.getString("material"),
                        rs.getInt("age_restrictions"),
                        rs.getInt("min_number_of_players"),
                        rs.getInt("max_number_of_players"),
                        rs.getInt("playing_time")
                );
                list.add(toy);
            }

            LoggerUtility.logInfo("✔ Завантажено " + list.size() + " настільних ігор.");

        } catch (SQLException e) {
            LoggerUtility.logError("Помилка під час завантаження всіх настільних ігор", e);
        }

        return list;
    }
}
