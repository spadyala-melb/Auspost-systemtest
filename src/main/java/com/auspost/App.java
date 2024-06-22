package com.auspost;

import java.util.Scanner;

/**
 * Take input from the user and calculate the difference in number of days between
 * the provided dates
 */

public class App {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            String input;

            while (true) {
                System.out.print("Please enter dates in 'DD MM YYYY, DD MM YYYY' format or 'exit' to stop: ");
                input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("Exited loop as user entered 'exit'.");
                    break;
                }

                String[] dates = input.split(",");
                if (dates.length == 2) {
                    DateDifferenceCalculator calculator = new DateDifferenceCalculator();
                    int difference = calculator.calculate(dates[0].trim(), dates[1].trim());
                    System.out.println("Difference between provided dates in days : " + input + ", " + difference);
                } else {
                    System.out.println("Invalid input format. Please enter dates in 'DD MM YYYY, DD MM YYYY' format.");
                }

            }
        }
    }

}
