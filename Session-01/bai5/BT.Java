package bai5;

public class BT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int d = scanner.nextInt();

        int tuSo = a * d + b * c;
        int mauSo = b * d;

        System.out.println("kết quả : " + tuSo + "/" + mauSo);
    }
}
