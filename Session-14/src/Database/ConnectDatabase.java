package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
  private static final String URL = "jdbc:mysql://localhost:3306/baitap_session17?createDatabaseIfNotExist=true&useSSL=false";
  private static final String USER = "root";
  private static final String PASSWORD = "tiendatMysql@2003";

  private ConnectDatabase() {
  }

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}
