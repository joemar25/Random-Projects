import java.util.Scanner;

public class DecimalToBinary {
    public static int decimalToBinary(int decimalNum) {
        if (decimalNum == 0) {
            return 0;
        } else {
            return (decimalNum % 2) + 10 * decimalToBinary(decimalNum / 2);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a decimal number: ");
        int decimalNum = scanner.nextInt();
        int binaryNum = decimalToBinary(decimalNum);

        System.out.println("The binary equivalent of " + decimalNum + " is " + binaryNum);
    }
}
