Package main.entity;

public class ClassDAO{
    
    public Class findById(Integer id) {
        String sql = "SELECT * FROM class WHERE id = ?;";
            try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToClass(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Class> findAll() {
        String sql = "SELECT * FROM class;";
        List<Class> clazz = new ArrayList<>();

        try (
            Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                clazz.add(resultSetToClass(rs));
            }

            return clazz;
        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Class resultSetToClass(ResultSet rs) throws SQLException {
        return new Class(
            rs.getInt("id"),
            rs.getString("name")
        );
    }

}