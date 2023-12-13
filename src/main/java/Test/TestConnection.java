package Test;
import java.sql.*;

public class TestConnection {
    public static void main(String[] args) throws SQLException {
        var url = "jdbc:mysql://localhost:3306/mydatabase";
        var user = "root";
        var password = "";
        var sql = "select * from hoanglam";
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            System.out.println(conn.getCatalog());
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            showInfo(resultSet);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    private static void showInfo(ResultSet rs){
        try{
            while(rs.next()){
                System.out.println(rs.getInt(1) + " - " + rs.getString(2)
                            + " - " + rs.getString(3) + " - " + rs.getDouble(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
