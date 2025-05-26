package back.Loader;

import back.Toy.BoardGames;
import back.Toy.Toy;
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
        List<Toy> result = load(toyId);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public List<Toy> load(int toyId) {
        List<Toy> list = new ArrayList<>();
        try {
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
                    list.add(toy);}}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<Toy> loadAll() {
        List<Toy> list = new ArrayList<>();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;}
}
