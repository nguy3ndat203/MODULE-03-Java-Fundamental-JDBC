package Bai06;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class OrderManager implements Manage<Order> {
  private final ArrayList<Order> orders = new ArrayList<>();

  @Override
  public void add(Order item) {
    orders.add(item);
    System.out.println("Thêm đơn hàng thành công!");
  }

  @Override
  public void update(int index, Order item) {
    orders.set(index, item);
    System.out.println("Cập nhật đơn hàng thành công!");
  }

  @Override
  public void delete(int index) {
    orders.remove(index);
    System.out.println("Xóa đơn hàng thành công!");
  }

  @Override
  public void display() {
    System.out.println("\n--- DANH SÁCH ĐƠN HÀNG ---");
    if (orders.isEmpty()) {
      System.out.println("Danh sách đơn hàng đang trống.");
      return;
    }
    for (int i = 0; i < orders.size(); i++) {
      System.out.println((i + 1) + ". " + orders.get(i));
    }
  }

  private int findIndexByOrderId(String orderId) {
    for (int i = 0; i < orders.size(); i++) {
      if (orders.get(i).getOrderId().equalsIgnoreCase(orderId)) {
        return i;
      }
    }
    throw new NoSuchElementException("Không tìm thấy đơn hàng có mã: " + orderId);
  }

  public static void main(String[] args) {
    OrderManager manager = new OrderManager();

    while (true) {
      System.out.println("\n=== MENU QUẢN LÝ ĐƠN HÀNG ===");
      System.out.println("1. Thêm đơn hàng");
      System.out.println("2. Sửa đơn hàng theo mã");
      System.out.println("3. Xóa đơn hàng theo mã");
      System.out.println("4. Hiển thị danh sách đơn hàng");
      System.out.println("0. Thoát");

      int choice = InputUtil.readInt("Chọn chức năng:", 0, 4);

      try {
        switch (choice) {
          case 1:
            String id = InputUtil.readString("Nhập mã đơn hàng:");
            String customerName = InputUtil.readString("Nhập tên khách hàng:");
            manager.add(new Order(id, customerName));
            break;

          case 2:
            if (manager.orders.isEmpty()) {
              throw new IllegalStateException("Danh sách trống, không có đơn hàng để sửa.");
            }
            manager.display();
            String updateId = InputUtil.readString("Nhập mã đơn hàng cần sửa:");
            int updateIndex = manager.findIndexByOrderId(updateId);
            String newCustomerName = InputUtil.readString("Nhập tên khách hàng mới:");
            manager.update(updateIndex, new Order(updateId, newCustomerName));
            break;

          case 3:
            if (manager.orders.isEmpty()) {
              throw new IllegalStateException("Danh sách trống, không có đơn hàng để xóa.");
            }
            manager.display();
            String deleteId = InputUtil.readString("Nhập mã đơn hàng cần xóa:");
            int deleteIndex = manager.findIndexByOrderId(deleteId);
            manager.delete(deleteIndex);
            break;

          case 4:
            manager.display();
            break;

          case 0:
            System.out.println("Thoát chương trình.");
            return;
        }
      } catch (NoSuchElementException | IllegalStateException e) {
        System.out.println("Lỗi thao tác: " + e.getMessage());
      } catch (Exception e) {
        System.out.println("Đã xảy ra lỗi không mong muốn: " + e.getMessage());
      }
    }
  }
}
