package Util;

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

  public static String readString(String prompt, String regex, String errorMessage) {
    while (true) {
      String input = readString(prompt);
      if (input.matches(regex)) {
        return input;
      }
      System.out.println(errorMessage);
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

  public static int readPositiveInt(String prompt) {
    while (true) {
      int value = readInt(prompt);
      if (value > 0) {
        return value;
      }
      System.out.println("Vui lòng nhập số nguyên dương (> 0) !");
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
