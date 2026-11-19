import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Nhập một số nguyên dương N: ");
    int songuyenN = scanner.nextInt();
    int sum = 0;
    if (songuyenN <= 0) {
      System.out.println("Số nhập vào không hợp lệ.");
    } else {
      for (int i = 1; i <= songuyenN; i++) {
        sum += i;
      }
      System.out.printf("Tổng các số từ 1 đến %d là: %d%n", N, sum);
    }
    scanner.close();
  }
}