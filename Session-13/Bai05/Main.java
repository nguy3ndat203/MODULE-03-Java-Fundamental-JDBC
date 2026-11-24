package Bai05;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {
    MovieManagement movieManagement = new MovieManagement();

    while (true) {
      System.out.println("\n*************** MENU QUẢN LÝ PHIM ***************");
      System.out.println("1. Thêm Phim");
      System.out.println("2. Liệt Kê Phim");
      System.out.println("3. Sửa Phim");
      System.out.println("4. Xóa Phim");
      System.out.println("5. Thoát");

      int choice = InputUtil.readInt("Lựa chọn của bạn:", 1, 5);

      try {
        switch (choice) {
          case 1:
            String title = InputUtil.readString("Nhập tiêu đề phim:");
            String director = InputUtil.readString("Nhập đạo diễn:");
            int year = InputUtil.readInt("Nhập năm phát hành:", 1888, 2100);
            movieManagement.addMovie(title, director, year);
            break;

          case 2:
            movieManagement.listMovies();
            break;

          case 3:
            int updateId = InputUtil.readPositiveInt("Nhập ID phim cần sửa:");
            String newTitle = InputUtil.readString("Nhập tiêu đề mới:");
            String newDirector = InputUtil.readString("Nhập đạo diễn mới:");
            int newYear = InputUtil.readInt("Nhập năm phát hành mới:", 1888, 2100);
            movieManagement.updateMovie(updateId, newTitle, newDirector, newYear);
            break;

          case 4:
            int deleteId = InputUtil.readPositiveInt("Nhập ID phim cần xóa:");
            movieManagement.deleteMovie(deleteId);
            break;

          case 5:
            System.out.println("Thoát chương trình.");
            return;
        }
      } catch (SQLException e) {
        System.out.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
      } catch (Exception e) {
        System.out.println("Đã xảy ra lỗi: " + e.getMessage());
      }
    }
  }
}
