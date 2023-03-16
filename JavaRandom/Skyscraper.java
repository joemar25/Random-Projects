import java.util.Scanner;

public class Skyscraper {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter width of skyscraper: ");
        int width = input.nextInt();

        System.out.print("Enter height of skyscraper: ");
        int height = input.nextInt();

        int base = width + 2;
        for (int i = 0; i < height; i++) {
            String top = (width % 2 == 1) ? "*" : "**";
            if (i == 0) {
                System.out.println(" " + centerString(top, width, " ") + " ");
            } else if (i == height - 1) {
                System.out.println("*".repeat(base));
            } else {
                System.out.println(" " + "*".repeat(width) + " ");
            }
        }
    }

    public static String centerString(String s, int width, String fill) {
        int padding = width - s.length();
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;
        return fill.repeat(leftPadding) + s + fill.repeat(rightPadding);
    }
}
