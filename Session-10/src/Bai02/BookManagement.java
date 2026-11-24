package Bai02;
import java.util.HashMap;
import java.util.Map;

public class BookManagement {
  private static final Map<String, Book> bookMap = new HashMap<>();

  public static void main(String[] args) {
    while (true) {
      System.out.println("\n*************** MENU QUẢN LÝ SÁCH ***************");
      System.out.println("1. Thêm sách");
      System.out.println("2. Tìm kiếm sách");
      System.out.println("3. Hiển thị danh sách sách");
      System.out.println("4. Thoát");

      int choice = InputUtil.readInt("Lựa chọn của bạn:", 1, 4);

      switch (choice) {
        case 1:
          addBook();
          break;
        case 2:
          searchBookByTitle();
          break;
        case 3:
          displayBooks();
          break;
        case 4:
          System.out.println("Thoát chương trình.");
          return;
      }
    }
  }

  private static void addBook() {
    String title = InputUtil.readString("Nhập tên sách:");
    String author = InputUtil.readString("Nhập tác giả:");
    int publishYear = InputUtil.readPositiveInt("Nhập năm xuất bản:");

    String key = title.toLowerCase();
    bookMap.put(key, new Book(title, author, publishYear));
    System.out.println("Sách đã được thêm thành công.");
  }

  private static void searchBookByTitle() {
    String keyword = InputUtil.readString("Nhập tên sách cần tìm:").toLowerCase();
    boolean found = false;

    for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
      if (entry.getKey().contains(keyword)) {
        System.out.println(entry.getValue());
        found = true;
      }
    }

    if (!found) {
      System.out.println("Không tìm thấy sách phù hợp.");
    }
  }

  private static void displayBooks() {
    System.out.println("\n--- DANH SÁCH SÁCH ---");
    if (bookMap.isEmpty()) {
      System.out.println("Danh sách sách trống.");
      return;
    }
    int index = 1;
    for (Book book : bookMap.values()) {
      System.out.println(index++ + ". " + book);
    }
  }
}
