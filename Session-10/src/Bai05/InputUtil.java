package Bai05;

import java.util.Scanner;

public class InputUtil {
  private static final Scanner SCANNER = new Scanner(System.in);

  public static String readString(String prompt) {
    while (true) {
      System.out.println(prompt);
      String input = SCANNER.nextLine().trim();
      if (!input.isEmpty()) {
        return input;
      }
      System.out.println("Vui lòng ko để trống !");
    }
  }

  public static int readInt(String prompt) {
    while (true) {
      System.out.println(prompt);
      String input = SCANNER.nextLine().trim();
      if (input.isEmpty()) {
        System.out.println("Vui lòng ko để trống !");
        continue;
      }
      try {
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("Vui lòng nhập số nguyên hợp lệ !");
      }
    }
  }

  public static int readInt(String prompt, int min, int max) {
    while (true) {
      int value = readInt(prompt);
      if (value >= min && value <= max) {
        return value;
      }
      System.out.println("Vui lòng nhập giá trị từ " + min + " đến " + max + " !");
    }
  }

  public static double readNonNegativeDouble(String prompt) {
    while (true) {
      System.out.println(prompt);
      String input = SCANNER.nextLine().trim();
      if (input.isEmpty()) {
        System.out.println("Vui lòng ko để trống !");
        continue;
      }
      try {
        double value = Double.parseDouble(input);
        if (value < 0) {
          System.out.println("Vui lòng nhập số thực >= 0 !");
        } else {
          return value;
        }
      } catch (NumberFormatException e) {
        System.out.println("Vui lòng nhập số thực >= 0 !");
      }
    }
  }
}
