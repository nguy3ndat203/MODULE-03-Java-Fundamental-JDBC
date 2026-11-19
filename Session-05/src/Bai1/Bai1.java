package Bai1;

import java.util.Scanner;

public class Bai1 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Nhập vào chuỗi: ");
    String text = scanner.nextLine().trim();

    System.out.print("Nhập từ cần tìm: ");
    String word = scanner.nextLine().trim();

    int index = text.indexOf(word);

    if (index != -1) {
      System.out.println("Từ \"" + word + "\" xuất hiện tại vị trí: " + index);
    } else {
      System.out.println("Từ \"" + word + "\" không xuất hiện trong chuỗi.");
    }

    scanner.close();
  }
}
