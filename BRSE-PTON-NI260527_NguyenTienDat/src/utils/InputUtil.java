package utils;

import java.util.Scanner;

public class InputUtil {
  private static final Scanner SCANNER = new Scanner(System.in);

  public static int readInt(String prompt) {
    while (true) {
      System.out.print(prompt);
      try {
        String input = SCANNER.nextLine();
        return Integer.parseInt(input.trim());
      } catch (NumberFormatException e) {
        System.out.println("❌ Lỗi: Vui lòng nhập vào một số nguyên hợp lệ!");
      }
    }
  }

  public static int readPositiveInt(String prompt) {
    while (true) {
      int value = readInt(prompt);
      if (value > 0) {
        return value;
      }
      System.out.println("Vui lòng nhập số nguyên dương (> 0) !");
    }
  }


  public static int readInt(String prompt, int min, int max) {
    while (true) {
      int value = readInt(prompt);
      if (value >= min && value <= max) {
        return value;
      }
      System.out.println("❌ Lỗi: Giá trị phải nằm trong khoảng từ " + min + " đến " + max + "!");
    }
  }

  public static String readString(String prompt) {
    while (true) {
      System.out.print(prompt);
      String input = SCANNER.nextLine();
      if (input != null && !input.trim().isEmpty()) {
        return input.trim();
      }
      System.out.println("❌ Lỗi: Nội dung không được để trống!");
    }
  }
}