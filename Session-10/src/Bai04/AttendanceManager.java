package Bai04;

import java.util.ArrayList;

public class AttendanceManager implements Manage<Student> {
  private final ArrayList<Student> students = new ArrayList<>();

  @Override
  public void add(Student item) {
    students.add(item);
    System.out.println("Thêm sinh viên thành công!");
  }

  @Override
  public void update(int index, Student item) {
    students.set(index, item);
    System.out.println("Cập nhật thông tin sinh viên thành công!");
  }

  @Override
  public void delete(int index) {
    students.remove(index);
    System.out.println("Xóa sinh viên thành công!");
  }

  @Override
  public void display() {
    System.out.println("\n--- DANH SÁCH ĐIỂM DANH ---");
    if (students.isEmpty()) {
      System.out.println("Danh sách sinh viên trống.");
      return;
    }
    for (int i = 0; i < students.size(); i++) {
      System.out.println((i + 1) + ". " + students.get(i));
    }
  }

  public int size() {
    return students.size();
  }

  public static void main(String[] args) {
    AttendanceManager manager = new AttendanceManager();

    while (true) {
      System.out.println("\n----------- MENU QUẢN LÝ ĐIỂM DANH -----------");
      System.out.println("1. Thêm sinh viên");
      System.out.println("2. Sửa sinh viên");
      System.out.println("3. Xóa sinh viên");
      System.out.println("4. Hiển thị danh sách sinh viên");
      System.out.println("5. Thoát");

      int choice = InputUtil.readInt("Lựa chọn của bạn:", 1, 5);

      try {
        switch (choice) {
          case 1:
            String id = InputUtil.readString("Nhập id sinh viên :");
            String name = InputUtil.readString("Nhập tên sinh viên:");
            manager.add(new Student(id, name));
            break;

          case 2:
            if (manager.size() == 0) {
              System.out.println("Danh sách trống, không thể sửa.");
              break;
            }
            manager.display();
            int updatePos = InputUtil.readInt("Nhập số thứ tự sinh viên cần sửa (1-" + manager.size() + "):");
            if (updatePos < 1 || updatePos > manager.size()) {
              throw new IndexOutOfBoundsException("Số thứ tự không nằm trong danh sách!");
            }
            String newId = InputUtil.readString("Nhập id sinh viên mới :");
            String newName = InputUtil.readString("Nhập tên sinh viên mới:");
            manager.update(updatePos - 1, new Student(newId, newName));
            break;

          case 3:
            if (manager.size() == 0) {
              System.out.println("Danh sách trống, không thể xóa.");
              break;
            }
            manager.display();
            int deletePos = InputUtil.readInt("Nhập số thứ tự sinh viên cần xóa (1-" + manager.size() + "):");
            if (deletePos < 1 || deletePos > manager.size()) {
              throw new IndexOutOfBoundsException("Số thứ tự không nằm trong danh sách!");
            }
            manager.delete(deletePos - 1);
            break;

          case 4:
            manager.display();
            break;

          case 5:
            System.out.println("Thoát chương trình.");
            return;
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.println("Lỗi chỉ mục: " + e.getMessage());
      } catch (Exception e) {
        System.out.println("Đã xảy ra lỗi: " + e.getMessage());
      }
    }
  }
}
