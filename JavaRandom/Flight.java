// Jerahmil Jay Felipe

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Flight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equalsIgnoreCase("3")) {
            printOptions();
            System.out.print("Select an option: ");
            input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.print("\nEnter Passenger Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Ticket Number: ");
                    String ticketNumber = scanner.nextLine();

                    System.out.print("Enter Date and Time of Departure: ");
                    String dateAndTime = scanner.nextLine();

                    System.out.print("Enter Airport Origin Code: ");
                    String originCode = scanner.nextLine();

                    System.out.print("Enter Airport Destination Code: ");
                    String destinationCode = scanner.nextLine();

                    try {
                        FileWriter writer = new FileWriter("db.txt", true);
                        writer.write(String.format("%s;%s;%s;%s;%s\n", name, ticketNumber, dateAndTime, originCode,
                                destinationCode));
                        writer.close();
                        System.out.println("\nRecord added successfully.");
                    } catch (Exception e) {
                        System.out.println("\nAn error occurred while adding the record.");
                    }
                    break;

                case "2":
                    System.out.print("\nSearch: ");
                    String searchField = scanner.nextLine();

                    try {
                        File file = new File("db.txt");
                        Scanner fileScanner = new Scanner(file);

                        boolean found = false;
                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            String[] parts = line.split(";");
                            String dbString = String.format("%-20s %-15s %-20s %-10s %-10s",
                                    parts[0],
                                    parts[1],
                                    parts[2],
                                    parts[3],
                                    parts[4]);

                            if (dbString.toLowerCase().contains(searchField.toLowerCase())) {
                                System.out.println(dbString);
                                found = true;
                            }
                        }

                        if (!found) {
                            System.out.println("No matching records found.");
                        }

                        fileScanner.close();
                    } catch (Exception e) {
                        System.out.println("\nAn error occurred while searching for records.");
                    }
                    break;

                case "3":
                    System.out.print("\nExiting program...");
                    break;

                default:
                    System.out.println("\nInvalid input. Please try again.");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }

    static void printOptions() {
        System.out.println("1. Write/Append to the program");
        System.out.println("2. Search and display the contents of the database [text file]");
        System.out.println("3. Exit");
    }
}
