import java.util.Scanner;

public class pre_final_act1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String inputString = scanner.nextLine().toLowerCase();

        String reversedString = new StringBuilder(inputString).reverse().toString();

        if (inputString.equals(reversedString)) {
            System.out.println("Amazing!");
        } else {
            System.out.println("Transformed data: " + reversedString);
        }

        int[] characterCounts = new int[26];
        for (char c : inputString.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                characterCounts[c - 'a']++;
            }
        }

        System.out.print("Character counts: ");
        for (int i = 0; i < characterCounts.length; i++) {
            if (characterCounts[i] > 0) {
                System.out.print((char) ('a' + i) + "=" + characterCounts[i] + " ");
            }
        }
        scanner.close();
    }
}
