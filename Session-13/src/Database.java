import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
  private static final String url = "jdbc:mysql://localhost:3306/BaiTap?createDatabaseIfNotExist=true&useSSL=false";
  private static final String user = "root";
  private static final String password = "tiendatMysql@2003";

  public static Connection getConnection() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection(url, user, password);
      if (connection != null) {
        System.out.println("Connect Successfully");
      } else {
        System.out.println("Connect thất bại");
      }
      return connection;
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }
}
