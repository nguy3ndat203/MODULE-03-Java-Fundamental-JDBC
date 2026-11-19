package Bai3;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Nhập một chuỗi: ");
    String inputString = scanner.nextLine();

    String replacedString = inputString.replaceAll("[0-9]", "*");

    System.out.println("Chuỗi sau khi thay thế: " + replacedString);
    scanner.close();
  }

}
