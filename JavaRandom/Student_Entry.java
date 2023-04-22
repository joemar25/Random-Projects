import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Student_Entry {

    // function to print options for the user
    static void printOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public static void main(String[] args) {

        String input = "";
        String[] options = { "Write/Append to the program", "Read and display the contents of the database [text file]",
                "Exit" };

        Scanner scanner = new Scanner(System.in);

        while (!input.equalsIgnoreCase("3")) {
            printOptions(options);

            System.out.print("Select an option: ");
            input = scanner.nextLine();

            switch (input) {
                case "1":
                    // input
                    System.out.println("\n(e.g. Juan De La Cruz)");
                    System.out.print("Student full name: ");
                    String stdFullName = scanner.nextLine();

                    System.out.println("\n(e.g. BSIT 1)");
                    System.out.print("Course and year: ");
                    String stdCourseYear = scanner.nextLine();

                    System.out.print("\nContact Number: ");
                    String stdContactNo = scanner.nextLine();

                    System.out.print("\nAddress: ");
                    String stdAddress = scanner.nextLine();

                    // write
                    try {
                        FileWriter writer = new FileWriter("FELIPE_finact1.txt", true);
                        writer.write(String.format("%s;;%s;;%s;;%s%n", stdFullName, stdCourseYear, stdContactNo,
                                stdAddress));
                        writer.close();
                        System.out.println("\nStudent details saved successfully.");
                    } catch (Exception e) {
                        System.out.println("\nAn error occurred while saving the details.");
                        e.printStackTrace();
                    }
                    break;

                case "2":
                    // read
                    try {
                        File file = new File("FELIPE_finact1.txt");
                        Scanner fileScanner = new Scanner(file);

                        System.out.println();
                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            String[] fields = line.split(";;");

                            String name = fields[0];
                            String course = fields[1];
                            String contact = fields[2];
                            String address = fields[3];

                            System.out.printf("%-20s %-10s %-15s %-20s%n", name, course, contact, address);
                        }

                        fileScanner.close();

                    } catch (Exception e) {
                        System.out.println("\nError: file not found.");
                    }
                    break;

                case "3":
                    // exit
                    System.out.print("\nExiting program...");
                    break;

                default:
                    // invalid input
                    System.out.println("\nInvalid input. Please try again.");
                    break;
            }
            System.out.println();
        }

        scanner.close();
    }

}

// Scanner (/)
// nextLine (/)
// split (/)
// Filewriter-append mode (/)