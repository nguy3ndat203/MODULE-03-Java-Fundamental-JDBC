package Bai04;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Nhập kích thước của mảng: ");
    int n = scanner.nextInt();

    if (n <= 0) {
      System.out.println("Kích thước rỗng");
      scanner.close();
      return;
    }

    int[] arr = new int[n];
    System.out.println("Nhập các phần tử cho mảng:");
    for (int i = 0; i < n; i++) {
      arr[i] = scanner.nextInt();
    }

    // Hiển thị mảng ban đầu
    System.out.println("Mảng ban đầu: " + Arrays.toString(arr));

    // Đảo ngược mảng bằng cách hoán đổi arr[i] với arr[n - 1 - i]
    for (int i = 0; i < n / 2; i++) {
      int temp = arr[i];
      arr[i] = arr[n - 1 - i];
      arr[n - 1 - i] = temp;
    }

    // 3. Hiển thị mảng sau khi đảo ngược
    System.out.println("Mảng sau khi đảo ngược: " + Arrays.toString(arr));

    scanner.close();
  }
}

