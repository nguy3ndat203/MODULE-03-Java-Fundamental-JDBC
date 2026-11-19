package Bai01;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // 1. Khởi tạo mảng
    System.out.print("Nhập kích thước mảng: ");
    int n = scanner.nextInt();

    int[] arr = new int[n];
    System.out.println("Nhập các phần tử cho mảng:");
    for (int i = 0; i < n; i++) {
      arr[i] = scanner.nextInt();
    }

    // 2. Sắp xếp mảng giảm dần bằng (Selection Sort)
    for (int i = 0; i < n - 1; i++) {
      int maxIndex = i;
      for (int j = i + 1; j < n; j++) {
        if (arr[j] > arr[maxIndex]) {
          maxIndex = j;
        }
      }

      if (maxIndex != i) {
        int temp = arr[i];
        arr[i] = arr[maxIndex];
        arr[maxIndex] = temp;
      }
    }

    int maxValue = arr[0];

    System.out.println("Mảng sau khi sắp xếp theo thứ tự giảm dần: " + Arrays.toString(arr));
    System.out.println("Phần tử lớn nhất trong mảng là: " + maxValue);

    scanner.close();
  }
}