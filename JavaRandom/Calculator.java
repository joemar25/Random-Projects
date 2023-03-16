import java.util.Scanner;

public class Calculator {
    public int addNum(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiplyNum(int a, int b) {
        return a * b;
    }

    public double divNum(int a, int b) {
        return (double) a / b;
    }

    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Please select an operation (+, -, *, /, exit): ");
            String operation = input.nextLine();

            if (operation.equals("exit")) {
                System.out.println("Exiting...");
                break;
            }

            System.out.print("Enter first number: ");
            int num1 = input.nextInt();

            System.out.print("Enter second number: ");
            int num2 = input.nextInt();
            input.nextLine(); // Consume the newline character left by nextInt()

            double result = 0;

            switch (operation) {
                case "+":
                    result = calculator.addNum(num1, num2);
                    System.out.println("Result: " + (int) result);
                    break;
                case "-":
                    result = calculator.subtract(num1, num2);
                    System.out.println("Result: " + (int) result);
                    break;
                case "*":
                    result = calculator.multiplyNum(num1, num2);
                    System.out.println("Result: " + (int) result);
                    break;
                case "/":
                    result = calculator.divNum(num1, num2);
                    System.out.println("Result: " + result);
                    break;
                default:
                    System.out.println("Invalid operation. Please try again.");
                    continue;
            }
        }
        input.close();
    }
}
