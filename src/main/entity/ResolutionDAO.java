package main.entity;

public class ResolutionDAO {

    public Aluno findById(Integer id) {
        String sql = "SELECT * FROM resolution WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToResolution(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<Resolution> findAll() {
        String sql = "SELECT * FROM resolution;";
        List<Resolution> resolution = new ArrayList<>();

        try (
            Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                resolution.add(resultSetToResolution(rs));
            }

            return resolution;
        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Resolution resultSetToResolution(ResultSet rs) throws SQLException {
        return new Resolution(
            rs.getInt("id"),
            rs.getInt("width"),
            rs.getInt("height")
        );
    }
}