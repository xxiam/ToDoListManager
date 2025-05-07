package org.example;
import java.util.Scanner;

public class ScannerMadeEasy {
    
    private Scanner scanner = new Scanner(System.in);

    private String red = "\u001B[31m";
    private String clear = "\u001B[0m";

    public String readString(String prompt) {
        System.out.print(prompt);
        boolean validInput = true;

        do {
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println(red + "Input cannot be empty. Please try again." + clear);
                    System.out.print(prompt);
                    validInput = false;
                } else {
                    return input;
                }
            } catch (Exception e) {
                System.out.println(red + "An error occurred while reading input. Please try again." + clear);
                validInput = false;
            }
        } while (!validInput);
        return null;
    }


    public int readInt(String prompt) {
        System.out.print(prompt);
        boolean validInput = true;

        do {
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println(red + "Input cannot be empty. Please try again." + clear);
                    validInput = false;
                } else {
                    return Integer.parseInt(input);
                }
            } catch (NumberFormatException e) {
                System.out.println(red + "Invalid input. Please enter a valid integer." + clear);
                validInput = false;
                System.out.print(prompt);
            } catch (Exception e) {
                System.out.println(red + "An error occurred while reading input. Please try again." + clear);
                validInput = false;
            }
        } while (!validInput);
        return -1;
    }

    public double readDouble(String prompt) {
        System.out.print(prompt);
        boolean validInput = true;

        do {
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println(red + "Input cannot be empty. Please try again." + clear);
                    validInput = false;
                } else {
                    return Double.parseDouble(input);
                }
            } catch (NumberFormatException e) {
                System.out.println(red + "Invalid input. Please enter a valid double." + clear);
                validInput = false;
                System.out.print(prompt);
            } catch (Exception e) {
                System.out.println(red + "An error occurred while reading input. Please try again." + clear);
                validInput = false;
            }
        } while (!validInput);
        return -1.0;
    }

    public void pauseTerminal() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

}
