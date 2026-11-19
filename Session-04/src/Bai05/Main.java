package Bai05;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Nhập số hàng và số cột
    System.out.print("Nhập số hàng: ");
    int rows = scanner.nextInt();

    System.out.print("Nhập số cột: ");
    int cols = scanner.nextInt();

    int[][] matrix = new int[rows][cols];

    System.out.println("Nhập các phần tử cho mảng theo từng hàng:");
    for (int i = 0; i < rows; i++) {
      System.out.print("Hàng " + (i + 1) + ": ");
      for (int j = 0; j < cols; j++) {
        matrix[i][j] = scanner.nextInt();
      }
    }

    //  Duyệt qua từng phần tử để tính tổng số chẵn và số lẻ
    int evenSum = 0;
    int oddSum = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (matrix[i][j] % 2 == 0) {
          evenSum += matrix[i][j];
        } else {
          oddSum += matrix[i][j];
        }
      }
    }

    System.out.println("Tổng các số chẵn là: " + evenSum);
    System.out.println("Tổng các số lẻ là: " + oddSum);

    scanner.close();
  }
}
