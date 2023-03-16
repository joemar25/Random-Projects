import java.util.Scanner;

public class ArraySum {

    public static void printSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        System.out.println("The sum of the elements is: " + sum);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter the elements.");
        for (int i = 0; i < n; i++) {
            System.out.print("[" + (i + 1) + "]=");
            arr[i] = scanner.nextInt();
        }

        printSum(arr);
    }

}
