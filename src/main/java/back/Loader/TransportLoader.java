package back.Loader;

import back.Toy.Transport;
import back.Toy.Toy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TransportLoader implements Loading{
    private final Connection conn;

    public TransportLoader(Connection conn) {
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
                String transportSql = "SELECT * FROM transport WHERE transport_id = ?";
                PreparedStatement transportStmt = conn.prepareStatement(transportSql);
                transportStmt.setInt(1, toyId);
                ResultSet transportRs = transportStmt.executeQuery();

                if (transportRs.next()) {
                    Transport toy = new Transport(
                            toyRs.getString("name"),
                            toyRs.getString("type"),
                            toyRs.getInt("price"),
                            toyRs.getInt("size"),
                            toyRs.getString("material"),
                            toyRs.getInt("age_restrictions"),
                            transportRs.getString("type_of_transport"),
                            transportRs.getInt("height"),
                            transportRs.getInt("lengthh"),
                            transportRs.getInt("width"),
                            transportRs.getInt("number_of_wheels"),
                            transportRs.getBoolean("on_electric_control")
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
                JOIN transport p ON t.toy_id = p.transport_id
            """;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transport toy = new Transport(
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("price"),
                        rs.getInt("size"),
                        rs.getString("material"),
                        rs.getInt("age_restrictions"),
                        rs.getString("type_of_transport"),
                        rs.getInt("height"),
                        rs.getInt("lengthh"),
                        rs.getInt("width"),
                        rs.getInt("number_of_wheels"),
                        rs.getBoolean("on_electric_control")

                );
                list.add(toy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
