package Bai04;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Student[] studentArray = new Student[3];

    studentArray[0] = new Student("Dat", 23, "A12", 8.5);
    studentArray[1] = new Student("Binh", 21, "A2", 3.0);
    studentArray[2] = new Student("Chuong", 20, "B2", 8.0);

    Scanner scanner = new Scanner(System.in);
    System.out.print("Nhập điểm min: ");
    double min = scanner.nextDouble();
    System.out.print("Nhập điểm max: ");
    double max = scanner.nextDouble();

    System.out.println("Danh sách sinh viên có điểm trung bình từ " + min + " đến " + max + ":");
    for (Student student : studentArray) {
      if (student.getAvgScore() >= min && student.getAvgScore() <= max) {
        System.out.println(student);
      }
    }

    scanner.close();
  }
}
