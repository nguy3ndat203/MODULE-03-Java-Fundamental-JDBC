import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Nhập một số nguyên N: ");
    int n = scanner.nextInt();

    if (n < 0) {
      n = -n;
    }
    int sum = 0;
    int temp = n;
    // Lặp để tách từng chữ số và cộng vào tổng
    while (temp > 0) {
      int digit = temp % 10;
      sum += digit;
      temp /= 10;
    }

    System.out.println("Tổng các chữ số là: " + sum);
    scanner.close();
  }
}