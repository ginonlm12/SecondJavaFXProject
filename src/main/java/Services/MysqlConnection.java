package Services;

import java.sql.*;
public class MysqlConnection {
    public static Connection getMysqlConnection() throws SQLException, ClassNotFoundException {
        String hostName = "demodbtuandao.mysql.database.azure.com";
        String dbName = "quanlychungcu";
        String userName = "tuan123deptrai";
        String password = "cyberpunk@123";
//        String hostName = "localhost";
//        String dbName = "mydatabase";
//        String userName = "root";
//        String password = "";
        return getMysqlConnection(hostName, dbName, userName, password);
    }

    public static Connection getMysqlConnection(String hostName, String dbName, String userName, String password)
            throws SQLException, ClassNotFoundException{
        //Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://" + hostName + ":3306/" + dbName; // + "?useUnicode=true&characterEncoding=utf-8";
        Connection conn = DriverManager.getConnection(connectionUrl, userName, password);
        return conn;
    }
}
