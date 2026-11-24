package StudentManager;

import Database.ConnectDatabase;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentManagement {
  public void addStudents(List<Student> students) {
    if (students == null || students.isEmpty()) {
      System.out.println("Danh sách sinh viên trống, không có bản ghi nào được thêm.");
      return;
    }

    String sql = "{CALL add_students(?, ?)}";
    Connection conn = null;

    try {
      conn = ConnectDatabase.getConnection();
      conn.setAutoCommit(false);

      try (CallableStatement cs = conn.prepareCall(sql)) {
        for (Student student : students) {
          if (student.getAge() <= 0) {
            throw new IllegalArgumentException("Tuổi của sinh viên '" + student.getName() + "' phải lớn hơn 0!");
          }
          cs.setString(1, student.getName());
          cs.setInt(2, student.getAge());
          cs.executeUpdate();
        }
      }

      conn.commit();
      System.out.println("Đã thêm sinh viên thành công.");

    } catch (Exception e) {
      if (conn != null) {
        try {
          conn.rollback();
          System.out.println("Đã rollback giao dịch do xảy ra lỗi!");
        } catch (SQLException rollbackEx) {
          System.out.println("Lỗi khi rollback: " + rollbackEx.getMessage());
        }
      }
      System.out.println("Thêm sinh viên thất bại: " + e.getMessage());
    } finally {
      if (conn != null) {
        try {
          conn.setAutoCommit(true);
          conn.close();
        } catch (SQLException closeEx) {
          System.out.println("Lỗi khi đóng kết nối: " + closeEx.getMessage());
        }
      }
    }
  }

  public boolean updateStudent(int id, String name, int age) throws SQLException {
    String sql = "{CALL update_student(?, ?, ?)}";
    try (Connection conn = ConnectDatabase.getConnection();
         CallableStatement cs = conn.prepareCall(sql)) {

      cs.setInt(1, id);
      cs.setString(2, name);
      cs.setInt(3, age);

      int rowsAffected = cs.executeUpdate();
      return rowsAffected > 0;
    }
  }

  public boolean deleteStudent(int id) throws SQLException {
    String sql = "{CALL delete_student(?)}";
    try (Connection conn = ConnectDatabase.getConnection();
         CallableStatement cs = conn.prepareCall(sql)) {

      cs.setInt(1, id);

      int rowsAffected = cs.executeUpdate();
      return rowsAffected > 0;
    }
  }

}
