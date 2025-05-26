package back.Loader;

import back.Toy.Doll;
import back.Toy.Toy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DollLoader implements Loading {

    private final Connection conn;

    public DollLoader(Connection conn) {
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
                String dollSql = "SELECT * FROM doll WHERE doll_id = ?";
                PreparedStatement dollStmt = conn.prepareStatement(dollSql);
                dollStmt.setInt(1, toyId);
                ResultSet dollRs = dollStmt.executeQuery();

                if (dollRs.next()) {
                    Doll toy = new Doll(
                            toyRs.getString("name"),
                            toyRs.getString("type"),
                            toyRs.getInt("price"),
                            toyRs.getInt("size"),
                            toyRs.getString("material"),
                            toyRs.getInt("age_restrictions"),
                            dollRs.getBoolean("sex"),
                            dollRs.getInt("height"),
                            dollRs.getInt("lengthh"),
                            dollRs.getInt("width"),
                            dollRs.getString("clothes_color"),
                            dollRs.getString("hair_color")
                    );
                    list.add(toy);
                }
            }
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
                JOIN doll p ON t.toy_id = p.doll_id
            """;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Doll toy = new Doll(
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("price"),
                        rs.getInt("size"),
                        rs.getString("material"),
                        rs.getInt("age_restrictions"),
                        rs.getBoolean("sex"),
                        rs.getInt("height"),
                        rs.getInt("lengthh"),
                        rs.getInt("width"),
                        rs.getString("clothes_color"),
                        rs.getString("hair_color")

                );
                list.add(toy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
