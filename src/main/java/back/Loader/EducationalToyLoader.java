package back.Loader;

import back.Toy.EducationalToy;
import back.Toy.Toy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EducationalToyLoader implements Loading{

    private final Connection conn;

    public EducationalToyLoader(Connection conn) {
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
                String educationalToySql = "SELECT * FROM educational_toy WHERE educational_toy_id = ?";
                PreparedStatement educationalToyStmt = conn.prepareStatement(educationalToySql);
                educationalToyStmt.setInt(1, toyId);
                ResultSet educationalToyRs = educationalToyStmt.executeQuery();

                if (educationalToyRs.next()) {
                    EducationalToy toy =new EducationalToy(
                            toyRs.getString("name"),
                            toyRs.getString("type"),
                            toyRs.getInt("price"),
                            toyRs.getInt("size"),
                            toyRs.getString("material"),
                            toyRs.getInt("age_restrictions"),
                            educationalToyRs.getInt("number_of_details")
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
