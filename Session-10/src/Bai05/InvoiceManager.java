package Bai05;

import java.util.ArrayList;

public class InvoiceManager implements Manage<Invoice> {
  private final ArrayList<Invoice> invoices = new ArrayList<>();

  @Override
  public void add(Invoice item) {
    invoices.add(item);
    System.out.println("Thêm hóa đơn thành công!");
  }

  @Override
  public void update(int index, Invoice item) {
    invoices.set(index, item);
    System.out.println("Cập nhật hóa đơn thành công!");
  }

  @Override
  public void delete(int index) {
    invoices.remove(index);
    System.out.println("Xóa hóa đơn thành công!");
  }

  @Override
  public void display() {
    System.out.println("\n--- DANH SÁCH HÓA ĐƠN ---");
    if (invoices.isEmpty()) {
      System.out.println("Danh sách hóa đơn trống.");
      return;
    }
    for (int i = 0; i < invoices.size(); i++) {
      System.out.println((i + 1) + ". " + invoices.get(i));
    }
  }

  public int size() {
    return invoices.size();
  }

  public static void main(String[] args) {
    InvoiceManager manager = new InvoiceManager();

    while (true) {
      System.out.println("\n*************** MENU QUẢN LÝ HÓA ĐƠN ***************");
      System.out.println("1. Thêm hóa đơn");
      System.out.println("2. Sửa hóa đơn");
      System.out.println("3. Xóa hóa đơn");
      System.out.println("4. Hiển thị danh sách hóa đơn");
      System.out.println("5. Thoát");

      int choice = InputUtil.readInt("Lựa chọn của bạn:", 1, 5);

      try {
        switch (choice) {
          case 1:
            String id = InputUtil.readString("Nhập mã hóa đơn:");
            double amount = InputUtil.readNonNegativeDouble("Nhập số tiền:");
            manager.add(new Invoice(id, amount));
            break;

          case 2:
            if (manager.size() == 0) {
              System.out.println("Danh sách trống, không thể sửa.");
              break;
            }
            manager.display();
            int updatePos = InputUtil.readInt("Nhập số thứ tự hóa đơn cần sửa (1-" + manager.size() + "):");
            if (updatePos < 1 || updatePos > manager.size()) {
              throw new IllegalArgumentException("Số thứ tự hóa đơn không hợp lệ!");
            }
            String newId = InputUtil.readString("Nhập mã hóa đơn mới:");
            double newAmount = InputUtil.readNonNegativeDouble("Nhập số tiền mới:");
            manager.update(updatePos - 1, new Invoice(newId, newAmount));
            break;

          case 3:
            if (manager.size() == 0) {
              System.out.println("Danh sách trống, không thể xóa.");
              break;
            }
            manager.display();
            int deletePos = InputUtil.readInt("Nhập số thứ tự hóa đơn cần xóa (1-" + manager.size() + "):");
            if (deletePos < 1 || deletePos > manager.size()) {
              throw new IllegalArgumentException("Số thứ tự hóa đơn không hợp lệ!");
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
      } catch (IllegalArgumentException e) {
        System.out.println("Lỗi dữ liệu: " + e.getMessage());
      } catch (Exception e) {
        System.out.println("Đã xảy ra lỗi: " + e.getMessage());
      }
    }
  }
}
