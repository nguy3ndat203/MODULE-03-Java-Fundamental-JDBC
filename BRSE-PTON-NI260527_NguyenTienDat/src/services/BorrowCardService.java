package services;


import Database.DbConnect;
import model.BorrowCard;
import utils.InputUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BorrowCardService {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  private Connection requireConnection() throws SQLException {
    Connection conn = DbConnect.getConn();
    if (conn == null || conn.isClosed()) {
      throw new SQLException("Chưa có kết nối hợp lệ tới cơ sở dữ liệu!");
    }
    return conn;
  }

  private Timestamp readTimestamp(String prompt) {
    while (true) {
      String raw = InputUtil.readString(prompt + " (yyyy-MM-dd HH:mm:ss):");
      try {
        LocalDateTime ldt = LocalDateTime.parse(raw, FORMATTER);
        return Timestamp.valueOf(ldt);
      } catch (DateTimeParseException e) {
        System.out.println("Sai định dạng thời gian! Ví dụ: 2026-11-25 14:30:00");
      }
    }
  }

  private String readStatus() {
    System.out.println("Chọn trạng thái: 1. Borrowing | 2. Returned | 3. Overdue");
    int opt = InputUtil.readInt("Lựa chọn trạng thái:", 1, 3);
    return switch (opt) {
      case 1 -> "Borrowing";
      case 2 -> "Returned";
      default -> "Overdue";
    };
  }

  private BorrowCard mapRow(ResultSet rs) throws SQLException {
    return new BorrowCard(
            rs.getLong("card_id"),
            rs.getString("book_title"),
            rs.getString("borrower_name"),
            rs.getTimestamp("borrow_date"),
            rs.getTimestamp("return_deadline"),
            rs.getInt("quantity"),
            rs.getString("status")
    );
  }

  private void printCards(String header, List<BorrowCard> cards) {
    System.out.println("\n--- " + header + " ---");
    if (cards.isEmpty()) {
      System.out.println("Không có dữ liệu phù hợp.");
      return;
    }
    for (BorrowCard c : cards) {
      System.out.println(c);
    }
  }

  // 1. Danh sách tất cả phiếu mượn
  public void showAllBorrowCards() {
    String sql = "{CALL get_all_borrow_cards()}";
    List<BorrowCard> cards = new ArrayList<>();

    try {
      Connection conn = requireConnection();
      try (CallableStatement cs = conn.prepareCall(sql);
           ResultSet rs = cs.executeQuery()) {
        while (rs.next()) {
          cards.add(mapRow(rs));
        }
      }
      printCards("DANH SÁCH TẤT CẢ PHIẾU MƯỢN", cards);
    } catch (SQLException e) {
      System.out.println("Lỗi khi lấy danh sách phiếu mượn: " + e.getMessage());
    }
  }

  // 2. Thêm mới phiếu mượn
  public void addBorrowCardFromInput() {
    String bookTitle = InputUtil.readString("Nhập tên sách mượn:");
    String borrowerName = InputUtil.readString("Nhập tên độc giả mượn:");
    Timestamp borrowDate = readTimestamp("Nhập ngày mượn sách");
    Timestamp returnDeadline = readTimestamp("Nhập hạn trả sách");
    int quantity = InputUtil.readPositiveInt("Nhập số lượng mượn (> 0):");
    String status = readStatus();

    String sql = "{CALL add_borrow_card(?, ?, ?, ?, ?, ?)}";

    try {
      Connection conn = requireConnection();
      try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setString(1, bookTitle);
        cs.setString(2, borrowerName);
        cs.setTimestamp(3, borrowDate);
        cs.setTimestamp(4, returnDeadline);
        cs.setInt(5, quantity);
        cs.setString(6, status);

        int rows = cs.executeUpdate();
        if (rows > 0) {
          System.out.println("Thêm mới phiếu mượn thành công!");
        } else {
          System.out.println("Không thể thêm phiếu mượn.");
        }
      }
    } catch (SQLException e) {
      System.out.println("Lỗi khi thêm phiếu mượn: " + e.getMessage());
    }
  }

  // 3. Cập nhật thông tin phiếu mượn theo card_id
  public void updateBorrowCardFromInput() {
    long cardId = InputUtil.readPositiveInt("Nhập mã phiếu mượn (card_id) cần cập nhật:");
    String bookTitle = InputUtil.readString("Nhập tên sách mới:");
    String borrowerName = InputUtil.readString("Nhập tên độc giả mới:");
    Timestamp borrowDate = readTimestamp("Nhập ngày mượn mới");
    Timestamp returnDeadline = readTimestamp("Nhập hạn trả mới");
    int quantity = InputUtil.readPositiveInt("Nhập số lượng mượn mới (> 0):");
    String status = readStatus();

    String sql = "{CALL update_borrow_card(?, ?, ?, ?, ?, ?, ?)}";

    try {
      Connection conn = requireConnection();
      try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setLong(1, cardId);
        cs.setString(2, bookTitle);
        cs.setString(3, borrowerName);
        cs.setTimestamp(4, borrowDate);
        cs.setTimestamp(5, returnDeadline);
        cs.setInt(6, quantity);
        cs.setString(7, status);

        int rows = cs.executeUpdate();
        if (rows > 0) {
          System.out.println("Cập nhật phiếu mượn có mã card_id = " + cardId + " thành công!");
        } else {
          System.out.println("Không tìm thấy phiếu mượn có card_id = " + cardId);
        }
      }
    } catch (SQLException e) {
      System.out.println("Lỗi khi cập nhật phiếu mượn: " + e.getMessage());
    }
  }

  // 4. Xóa phiếu mượn theo card_id
  public void deleteBorrowCardFromInput() {
    long cardId = InputUtil.readPositiveInt("Nhập mã phiếu mượn (card_id) cần xóa:");
    String confirm = InputUtil.readString("Xác nhận xóa phiếu mượn mã " + cardId + "? (y/n):");

    if (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("yes")) {
      System.out.println("Đã hủy thao tác xóa.");
      return;
    }

    String sql = "{CALL delete_borrow_card(?)}";

    try {
      Connection conn = requireConnection();
      try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setLong(1, cardId);
        int rows = cs.executeUpdate();
        if (rows > 0) {
          System.out.println("Xóa phiếu mượn có card_id = " + cardId + " thành công!");
        } else {
          System.out.println("Không tìm thấy phiếu mượn có card_id = " + cardId);
        }
      }
    } catch (SQLException e) {
      System.out.println("Lỗi khi xóa phiếu mượn: " + e.getMessage());
    }
  }

  // 5. Tìm kiếm phiếu mượn theo tên độc giả
  public void searchByBorrowerNameFromInput() {
    String keyword = InputUtil.readString("Nhập tên độc giả cần tìm:");
    String sql = "{CALL get_borrow_cards_by_borrower(?)}";
    List<BorrowCard> cards = new ArrayList<>();

    try {
      Connection conn = requireConnection();
      try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setString(1, keyword);
        try (ResultSet rs = cs.executeQuery()) {
          while (rs.next()) {
            cards.add(mapRow(rs));
          }
        }
      }
      printCards("TÌM KIẾM THEO ĐỘC GIẢ: '" + keyword + "'", cards);
    } catch (SQLException e) {
      System.out.println("Lỗi khi tìm kiếm theo tên độc giả: " + e.getMessage());
    }
  }

  // 6. Tìm kiếm phiếu mượn theo tên sách
  public void searchByBookTitleFromInput() {
    String keyword = InputUtil.readString("Nhập tên sách cần tìm:");
    String sql = "{CALL search_borrow_cards_by_book_title(?)}";
    List<BorrowCard> cards = new ArrayList<>();

    try {
      Connection conn = requireConnection();
      try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setString(1, keyword);
        try (ResultSet rs = cs.executeQuery()) {
          while (rs.next()) {
            cards.add(mapRow(rs));
          }
        }
      }
      printCards("TÌM KIẾM THEO TÊN SÁCH: '" + keyword + "'", cards);
    } catch (SQLException e) {
      System.out.println("Lỗi khi tìm kiếm theo tên sách: " + e.getMessage());
    }
  }
}