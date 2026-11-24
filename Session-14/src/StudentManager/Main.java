package StudentManager;

import Util.InputUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    StudentManagement manager = new StudentManagement();

    while (true) {
      System.out.println("\n*************** MENU QUẢN LÝ SINH VIÊN ***************");
      System.out.println("1. Thêm nhanh danh sách sinh viên mẫu (Transaction)");
      System.out.println("2. Nhập tay nhiều sinh viên để thêm (Transaction)");
      System.out.println("3. Cập nhật thông tin sinh viên theo ID");
      System.out.println("4. Xóa sinh viên theo ID");
      System.out.println("5. Thoát");

      int choice = InputUtil.readInt("Lựa chọn của bạn:", 1, 5);

      try {
        switch (choice) {
          case 1:
            List<Student> sampleStudents = Arrays.asList(
                    new Student(0, "Nguyễn Văn A", 20),
                    new Student(0, "Trần Thị B", 22),
                    new Student(0, "Lê Văn C", 19)
            );
            manager.addStudents(sampleStudents);
            break;

          case 2:
            int count = InputUtil.readInt("Nhập số lượng sinh viên muốn thêm (tối thiểu 3):", 3, 100);
            List<Student> customStudents = new ArrayList<>();
            for (int i = 1; i <= count; i++) {
              System.out.println("--- Sinh viên thứ " + i + " ---");
              String name = InputUtil.readString("Nhập tên sinh viên:");
              int age = InputUtil.readInt("Nhập tuổi sinh viên:", 1, 120);
              customStudents.add(new Student(0, name, age));
            }
            manager.addStudents(customStudents);
            break;

          case 3:
            int updateId = InputUtil.readPositiveInt("Nhập ID sinh viên cần cập nhật:");
            String newName = InputUtil.readString("Nhập tên mới:");
            int newAge = InputUtil.readInt("Nhập tuổi mới:", 1, 120);
            boolean updated = manager.updateStudent(updateId, newName, newAge);
            if (updated) {
              System.out.println("Cập nhật sinh viên có ID = " + updateId + " thành công!");
            } else {
              System.out.println("Không tìm thấy sinh viên có ID = " + updateId + " để cập nhật.");
            }
            break;

          case 4:
            int deleteId = InputUtil.readPositiveInt("Nhập ID sinh viên cần xóa:");
            boolean deleted = manager.deleteStudent(deleteId);
            if (deleted) {
              System.out.println("Xóa sinh viên có ID = " + deleteId + " thành công!");
            } else {
              System.out.println("Không tìm thấy sinh viên có ID = " + deleteId + " để xóa.");
            }
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