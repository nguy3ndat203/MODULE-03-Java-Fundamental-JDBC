import Database.DbConnect;
import services.BorrowCardService;
import utils.InputUtil;

public class Main {
  public static void main(String[] args) {
    BorrowCardService service = new BorrowCardService();

    while (true) {
      System.out.println("=============== LIBRARY MANAGEMENT ===============");
      System.out.println("1. Danh sách tất cả phiếu mượn");
      System.out.println("2. Thêm mới phiếu mượn");
      System.out.println("3. Cập nhật thông tin phiếu mượn");
      System.out.println("4. Xóa phiếu mượn");
      System.out.println("5. Tìm kiếm phiếu mượn theo tên độc giả");
      System.out.println("6. Tìm kiếm phiếu mượn theo tên sách");
      System.out.println("7. Thoát");

      int choice = InputUtil.readInt("Lựa chọn của bạn:", 1, 7);

      switch (choice) {
        case 1 -> service.showAllBorrowCards();
        case 2 -> service.addBorrowCardFromInput();
        case 3 -> service.updateBorrowCardFromInput();
        case 4 -> service.deleteBorrowCardFromInput();
        case 5 -> service.searchByBorrowerNameFromInput();
        case 6 -> service.searchByBookTitleFromInput();
        case 7 -> {
          DbConnect.close();
          System.out.println("Cảm ơn bạn đã sử dụng chương trình ^.^");
          return;
        }
      }
    }
  }
}