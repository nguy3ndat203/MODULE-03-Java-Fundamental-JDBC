package Bai7;

import java.util.Random;
import java.util.Scanner;

public class Main {
  private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Nhập độ dài chuỗi n (1 <= n <= 1000): ");
    if (!scanner.hasNextInt()) {
      System.out.println("Giá trị n không hợp lệ.");
      scanner.close();
      return;
    }

    int n = scanner.nextInt();
    if (n < 1 || n > 1000) {
      System.out.println("Giá trị n phải nằm trong khoảng từ 1 đến 1000.");
      scanner.close();
      return;
    }

    String randomStr = generateRandomString(n);
    System.out.println("Chuỗi ngẫu nhiên (độ dài " + randomStr.length() + "): " + randomStr);

    scanner.close();
  }

  public static String generateRandomString(int n) {
    Random random = new Random();
    StringBuilder sb = new StringBuilder(n);

    for (int i = 0; i < n; i++) {
      int randomIndex = random.nextInt(CHAR_POOL.length());
      sb.append(CHAR_POOL.charAt(randomIndex));
    }

    return sb.toString();
  }
}
