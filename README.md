# SecondJavaFXProject
Incomplete project
# Steps to run the application
  1. Download the database file from the database folder, create a database and import the database in MySQL using XAMPP.
  2. Edit the hostName, dbName, userName, password fields in the Services.MysqlConnection class to match your MySQL credentials on your computer.
    For example:

      public static Connection getMysqlConnection() throws SQLException, ClassNotFoundException {
          String hostName = "localhost";
          String dbName = "mydatabase";
          String userName = "root";
          String password = "";
          return getMysqlConnection(hostName, dbName, userName, password);
      }
     
  4. Download the latest OpenJFX library from the homepage https://gluonhq.com/products/javafx.
  5. Download the MySQL connector library from 8.0 and above, you can download this library from the Maven Project page https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.22.
  6. Add all of them to the libraries in Java to be able to run the Project.
  7. Log in with the default account.
