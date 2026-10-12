package bai6;

public class BT {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        float width = scanner.nextFloat();
        float height = scanner.nextFloat();

        float area = width * height;
        float circumference = 2 * (width + height);

        System.out.printf("Diện tích : %.2f\n", area);
        System.out.printf("Chu vi : %.2f\n", circumference);
    }
}
