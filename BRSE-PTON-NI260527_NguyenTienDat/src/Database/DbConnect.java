package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
  private static final String URL = "jdbc:mysql://localhost:3306/db_library_management?createDatabaseIfNotExist=true&useSSL=false";
  private static final String USER = "root";
  private static final String PASSWORD = "tiendatMysql@2003";

  private static Connection conn;

  static {
    try {
      conn = DriverManager.getConnection(URL, USER, PASSWORD);
      System.out.println("Kết nối CSDL thành công!");
    } catch (SQLException e) {
      System.out.println("Kết nối thất bại: " + e.getMessage());
    }
  }

  public static Connection getConn() {
    return conn;
  }

  public static void close() {
    try {
      if (conn != null) {
        conn.close();
      }
    } catch (SQLException e) {
      System.out.println("Lỗi khi đóng kết nối: " + e.getMessage());
    }
  }
}
