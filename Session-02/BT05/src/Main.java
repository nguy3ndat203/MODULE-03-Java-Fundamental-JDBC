import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Nhập một số nguyên từ 1 đến 12: ");

    if (!scanner.hasNextInt()) {
      System.out.println("Tháng không hợp lệ.");
      scanner.close();
      return;
    }

    int month = scanner.nextInt();
    String monthName;
    String daysInfo;

    switch (month) {
      case 1:
        monthName = "Tháng 1";
        daysInfo = "31 ngày";
        break;
      case 2:
        monthName = "Tháng 2";
        daysInfo = "28 hoặc 29 ngày";
        break;
      case 3:
        monthName = "Tháng 3";
        daysInfo = "31 ngày";
        break;
      case 4:
        monthName = "Tháng 4";
        daysInfo = "30 ngày";
        break;
      case 5:
        monthName = "Tháng 5";
        daysInfo = "31 ngày";
        break;
      case 6:
        monthName = "Tháng 6";
        daysInfo = "30 ngày";
        break;
      case 7:
        monthName = "Tháng 7";
        daysInfo = "31 ngày";
        break;
      case 8:
        monthName = "Tháng 8";
        daysInfo = "31 ngày";
        break;
      case 9:
        monthName = "Tháng 9";
        daysInfo = "30 ngày";
        break;
      case 10:
        monthName = "Tháng 10";
        daysInfo = "31 ngày";
        break;
      case 11:
        monthName = "Tháng 11";
        daysInfo = "30 ngày";
        break;
      case 12:
        monthName = "Tháng 12";
        daysInfo = "31 ngày";
        break;
      default:
        System.out.println("Tháng không hợp lệ.");
        scanner.close();
        return;
    }
    System.out.println(monthName + " có " + daysInfo + ".");
    scanner.close();
  }
}