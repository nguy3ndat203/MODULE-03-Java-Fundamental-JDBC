package Bai03;
import java.util.Iterator;
import java.util.LinkedList;

public class UserManagement {
  private static final LinkedList<User> userList = new LinkedList<>();
  private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
  private static final String PHONE_REGEX = "^\\d{9,11}$";

  public static void main(String[] args) {
    while (true) {
      System.out.println("\n*************** MENU QUẢN LÝ NGƯỜI DÙNG ***************");
      System.out.println("1. Thêm người dùng");
      System.out.println("2. Xóa người dùng");
      System.out.println("3. Hiển thị danh sách người dùng");
      System.out.println("4. Thoát");

      int choice = InputUtil.readInt("Lựa chọn của bạn:", 1, 4);

      try {
        switch (choice) {
          case 1:
            addUser();
            break;
          case 2:
            deleteUserByEmail();
            break;
          case 3:
            displayUsers();
            break;
          case 4:
            System.out.println("Thoát chương trình.");
            return;
        }
      } catch (Exception e) {
        System.out.println("Đã xảy ra lỗi: " + e.getMessage());
      }
    }
  }

  private static void addUser() {
    String name = InputUtil.readString("Nhập tên người dùng:");
    String email = InputUtil.readString(
            "Nhập email người dùng:",
            EMAIL_REGEX,
            "Email không hợp lệ! Vui lòng nhập lại (ví dụ: ten@gmail.com)."
    );
    String phone = InputUtil.readString(
            "Nhập số điện thoại người dùng:",
            PHONE_REGEX,
            "Số điện thoại không hợp lệ! Vui lòng nhập từ 9 đến 11 chữ số."
    );

    userList.add(new User(name, email, phone));
    System.out.println("Thêm người dùng thành công!");
  }

  private static void deleteUserByEmail() {
    if (userList.isEmpty()) {
      System.out.println("Danh sách người dùng trống, không thể xóa.");
      return;
    }

    String emailToDelete = InputUtil.readString("Nhập email người dùng cần xóa:");
    boolean removed = false;

    Iterator<User> iterator = userList.iterator();
    while (iterator.hasNext()) {
      User user = iterator.next();
      if (user.getEmail().equalsIgnoreCase(emailToDelete)) {
        iterator.remove();
        removed = true;
        break;
      }
    }

    if (removed) {
      System.out.println("Đã xóa người dùng có email: " + emailToDelete);
    } else {
      System.out.println("Không tìm thấy người dùng với email: " + emailToDelete);
    }
  }

  private static void displayUsers() {
    System.out.println("\n--- DANH SÁCH NGƯỜI DÙNG ---");
    if (userList.isEmpty()) {
      System.out.println("Danh sách trống.");
      return;
    }
    int index = 1;
    for (User user : userList) {
      System.out.println(index++ + ". " + user);
    }
  }
}