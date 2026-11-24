package Bai01;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagement {
  private static final Scanner scanner = new Scanner(System.in);
  private static final ArrayList<Student> students = new ArrayList<>();

  public static void main(String[] args) {
    while (true) {
      System.out.println("\n=== QUẢN LÝ DANH SÁCH SINH VIÊN ===");
      System.out.println("1. Thêm sinh viên");
      System.out.println("2. Hiển thị danh sách sinh viên");
      System.out.println("0. Thoát");
      System.out.print("Chọn chức năng: ");

      String choiceInput = scanner.nextLine().trim();
      int choice;
      try {
        choice = Integer.parseInt(choiceInput);
      } catch (NumberFormatException e) {
        System.out.println("Vui lòng nhập số nguyên hợp lệ!");
        continue;
      }

      switch (choice) {
        case 1:
          addStudent();
          break;
        case 2:
          displayStudents();
          break;
        case 0:
          System.out.println("Thoát chương trình.");
          scanner.close();
          return;
        default:
          System.out.println("Lựa chọn không hợp lệ!");
      }
    }
  }

  private static void addStudent() {
    String name = inputNonEmptyString("Nhập tên sinh viên: ");
    int age = inputNonNegativeInt("Nhập tuổi sinh viên: ");
    double gpa = inputNonNegativeDouble("Nhập điểm trung bình sinh viên: ");

    students.add(new Student(name, age, gpa));
    System.out.println("Thêm sinh viên thành công!");
  }

  private static void displayStudents() {
    System.out.println("\n--- DANH SÁCH SINH VIÊN ---");
    if (students.isEmpty()) {
      System.out.println("Danh sách trống.");
      return;
    }
    for (int i = 0; i < students.size(); i++) {
      System.out.println((i + 1) + ". " + students.get(i));
    }
  }

  private static String inputNonEmptyString(String prompt) {
    while (true) {
      System.out.println(prompt);
      String input = scanner.nextLine().trim();
      if (input.isEmpty()) {
        System.out.println("Vui lòng ko để trống !");
      } else {
        return input;
      }
    }
  }

  private static int inputNonNegativeInt(String prompt) {
    while (true) {
      System.out.println(prompt);
      String input = scanner.nextLine().trim();
      if (input.isEmpty()) {
        System.out.println("Vui lòng ko bỏ trống !");
        continue;
      }
      try {
        int value = Integer.parseInt(input);
        if (value < 0) {
          System.out.println("Vui lòng nhập số nguyên >= 0 !");
        } else {
          return value;
        }
      } catch (NumberFormatException e) {
        System.out.println("Vui lòng nhập số nguyên >= 0 !");
      }
    }
  }

  private static double inputNonNegativeDouble(String prompt) {
    while (true) {
      System.out.println(prompt);
      String input = scanner.nextLine().trim();
      if (input.isEmpty()) {
        System.out.println("Vui lòng ko bỏ trống !");
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
