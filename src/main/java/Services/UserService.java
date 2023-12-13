package Services;

import Controllers.InformationController;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserService {
    public static User showInfor(User user) throws ClassNotFoundException, SQLException {
        String username = user.username;
        Connection conn = MysqlConnection.getMysqlConnection();
        Statement stmt = conn.createStatement();
        var sql = "select * from up where username = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, username);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            user = new User();

            user.username = resultSet.getString("username");
            user.email = resultSet.getString("email");
            user.name = resultSet.getString("name");
            user.cpa = resultSet.getDouble("cpa");
            user.province = resultSet.getString("province");
            user.dob = resultSet.getString("dob");
            user.gender = resultSet.getString("gender");
            user.university = resultSet.getString("university");
            user.role = resultSet.getString("role");

        }
        stmt.close();
        conn.close();
        return user;
    }
    public static User inforSave(User user)throws ClassNotFoundException, SQLException {
        User user1 = new User();
        String username = user.username;
        Double cpa = user.getCpa();
        String province = user.getProvince();
        String gender = user.getGender();
        String dob = user.getDob();
        String university = user.getUniversity();

        Connection conn = MysqlConnection.getMysqlConnection();
        Statement stmt = conn.createStatement();

        var sql = "UPDATE up SET cpa = ?, province = ?, gender = ?, dob = ?, university = ? WHERE username = ?;";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setDouble(1, cpa);
        preparedStatement.setString(2, province);
        preparedStatement.setString(3, gender);
        preparedStatement.setString(4, dob);
        preparedStatement.setString(5, university);
        preparedStatement.setString(6, username);

        int addedRows = preparedStatement.executeUpdate();
        if (addedRows > 0) {
            user1.setUsername(user.getUsername());
            user1.setProvince(province);
            user1.setCpa(Double.valueOf(cpa));
            user1.setGender(gender);
            user1.setDob(dob);
            user1.setUniversity(university);

            return user1;
        }
        else{
            user1 = null;
        }
        stmt.close();
        conn.close();

        return user1;
    }

    public static User getAuthenticatedUser(User user) throws ClassNotFoundException, SQLException{
        String username = user.getUsername();
        String password = user.getPassword();

        Connection conn = MysqlConnection.getMysqlConnection();
        Statement stmt = conn.createStatement();
        var sql = "select * from up where username = ? and password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setString(1, String.valueOf(username));
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
        user = new User();
        return user;
    }

    public static User addUsertoDatabase(User user) throws ClassNotFoundException, SQLException{
        User user1 = new User();
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String name = user.getName();

        Connection conn = MysqlConnection.getMysqlConnection();
        Statement stmt = conn.createStatement();
        var sql_1 = "INSERT INTO up (username, password, name, email) " +
                "VALUES(?, ?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(sql_1);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, name);
        preparedStatement.setString(4, email);

        int addedRows = preparedStatement.executeUpdate();
        if (addedRows > 0) {
            user1.setName(name);
            user1.setPassword(password);
            user1.setUsername(username);
            user1.setEmail(email);
        } else {
            user1 = null;
        }
        return user1;
    }

    public static User changeUsertoDatabase(User user) throws ClassNotFoundException, SQLException{
        User user1 = new User();
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String name = user.getName();

        Connection conn = MysqlConnection.getMysqlConnection();
        Statement stmt = conn.createStatement();
        var sql_2 = "UPDATE up SET password = ? WHERE name = ? AND username = ? AND email = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(sql_2);
        preparedStatement.setString(1, password);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, username);
        preparedStatement.setString(4, email);

        int addedRows = preparedStatement.executeUpdate();
        if (addedRows > 0) {
            user1.setName(name);
            user1.setPassword(password);
            user1.setUsername(username);
            user1.setEmail(email);
        }
        else{
            user1 = null;
        }

        return user1;
    }
    public static ObservableList<User> loadDatafromDatabase(User user) throws ClassNotFoundException, SQLException{
        ObservableList<User> data = FXCollections.observableArrayList();
        String university = user.getUniversity();
        String role = user.getRole();
        Connection conn = MysqlConnection.getMysqlConnection();

        Statement stmt = conn.createStatement();
        if (university.equals("All universities in HaNoi")){
            var sql = "select * from up order by dob asc, cpa desc";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double cpa = resultSet.getDouble("cpa");
                String province = resultSet.getString("province");
                String dob = resultSet.getString("dob");
                if (dob.equals("1900-01-01")) dob = "Unknown";
                if (cpa != 0.0) data.add(new User(name, cpa, dob, province));
            }
        }
        else{
            var sql = "select * from up where university = ? order by dob asc, cpa desc";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, university);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double cpa = resultSet.getDouble("cpa");
                String province = resultSet.getString("province");
                String dob = resultSet.getString("dob");
                if (dob.equals("1900-01-01")) dob = "Unknown";
                if (cpa != 0.0) data.add(new User(name, cpa, dob, province));
            }
        }
        return data;
    }
    public static ObservableList<String> ShowUsername() throws ClassNotFoundException, SQLException{
        ObservableList<String> SUAusername = FXCollections.observableArrayList();
        SUAusername.add("DinhMinh");
        Connection conn = MysqlConnection.getMysqlConnection();
        Statement stmt = conn.createStatement();

        var sql = "select * from up where role != 'Admin'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            SUAusername.add(resultSet.getString("username"));
        }

        stmt.close();
        conn.close();
        return SUAusername;
    }

    public static boolean setAdmin(User user) throws ClassNotFoundException, SQLException{
        String username = user.getUsername();
        Connection conn = MysqlConnection.getMysqlConnection();
        Statement stmt = conn.createStatement();

        var sql = "UPDATE up SET role = ? WHERE username = ? AND role != 'Admin'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, "Admin");
        preparedStatement.setString(2, username);

        int addedRows = preparedStatement.executeUpdate();
        if (addedRows > 0) {
            return true;
        } else {
            return false;
        }
    }
}
