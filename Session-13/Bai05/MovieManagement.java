package Bai05;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieManagement {
  private static final String URL = "jdbc:mysql://localhost:3306/BaiTap05?createDatabaseIfNotExist=true&useSSL=false";
  private static final String USER = "root";
  private static final String PASSWORD = "tiendatMysql@2003";

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }

  public void addMovie(String title, String director, int year) throws SQLException {
    String sql = "{CALL add_movie(?, ?, ?)}";
    try (Connection conn = getConnection();
         CallableStatement cs = conn.prepareCall(sql)) {

      cs.setString(1, title);
      cs.setString(2, director);
      cs.setInt(3, year);

      int rows = cs.executeUpdate();
      if (rows > 0) {
        System.out.println("Thêm phim thành công!");
      }
    }
  }

  public List<Movie> listMovies() throws SQLException {
    List<Movie> movies = new ArrayList<>();
    String sql = "{CALL list_movies()}";

    try (Connection conn = getConnection();
         CallableStatement cs = conn.prepareCall(sql);
         ResultSet rs = cs.executeQuery()) {

      while (rs.next()) {
        movies.add(new Movie(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("director"),
                rs.getInt("release_year")
        ));
      }
    }

    System.out.println("\n--- DANH SÁCH PHIM ---");
    if (movies.isEmpty()) {
      System.out.println("Danh sách phim trống.");
    } else {
      for (Movie m : movies) {
        System.out.println(m);
      }
    }
    return movies;
  }

  public void updateMovie(int id, String title, String director, int year) throws SQLException {
    String sql = "{CALL update_movie(?, ?, ?, ?)}";
    try (Connection conn = getConnection();
         CallableStatement cs = conn.prepareCall(sql)) {

      cs.setInt(1, id);
      cs.setString(2, title);
      cs.setString(3, director);
      cs.setInt(4, year);

      int rows = cs.executeUpdate();
      if (rows > 0) {
        System.out.println("Cập nhật phim thành công!");
      } else {
        System.out.println("Không tìm thấy phim có ID = " + id);
      }
    }
  }

  public void deleteMovie(int id) throws SQLException {
    String sql = "{CALL delete_movie(?)}";
    try (Connection conn = getConnection();
         CallableStatement cs = conn.prepareCall(sql)) {

      cs.setInt(1, id);

      int rows = cs.executeUpdate();
      if (rows > 0) {
        System.out.println("Xóa phim thành công!");
      } else {
        System.out.println("Không tìm thấy phim có ID = " + id);
      }
    }
  }
}
