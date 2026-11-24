package Bai07;

public class Main {
  public static void main(String[] args) {
    Employee emp = new Employee("Nguyen Van Dat", "E01", 1000.0);
    Manager mgr = new Manager("Tran Thi B", "M01", 2000.0, 500.0);
    Developer dev = new Developer("Le Van C", "D01", 1500.0, "Java");

    System.out.println("=== THÔNG TIN BAN ĐẦU ===");
    System.out.println(emp.toString() + " -> Lương thực nhận: " + emp.getSalary());
    System.out.println(mgr.toString() + " -> Lương thực nhận: " + mgr.getSalary());
    System.out.println(dev.toString() + " -> Lương thực nhận: " + dev.getSalary());

    System.out.println("\n--- SAU KHI TĂNG LƯƠNG CHO NHÂN VIÊN E01 ---");
    emp.increaseSalary(300.0);
    System.out.println(emp.toString() + " -> Lương thực nhận: " + emp.getSalary());
  }
}
