package com.codecool.ui;

import java.util.Scanner;


public class IO {
    public Scanner scan;

    public IO() {
        scan = new Scanner(System.in);
        scan.useDelimiter(System.lineSeparator());
    }

    public String gatherInput() {
        boolean validInput = true;
        String userInput;
        do {
            if (!validInput) {
                System.out.println("Your input must contain at least one character. Enter again: ");
            }
            validInput = false;
            userInput = scan.next().toUpperCase();
            if (!userInput.equals("")) {
                validInput = true;
            }
        } while (!validInput);
        return userInput;
    }

    public void gatherEmptyInput() {
        scan.next();
    }

    public int gatherIntInput(int rangeMin, int rangeMax) {
        String userInput = "";
        boolean validInput = false;
        while (!validInput) {
            userInput = scan.next();
            validInput = isNumberInRange(userInput, rangeMin, rangeMax);
        }
        return Integer.parseInt(userInput);
    }

    private boolean isNumberInRange(String userInput, int rangeMin, int rangeMax) {
        int userInt;
        if (!userInput.equals("")) {
            if (userInput.matches("^[0-9]*$")) {
                userInt = Integer.parseInt(userInput);
                return userInt >= rangeMin && userInt <= rangeMax;
            }
        }
        System.out.println("Invalid input, please try again: ");
        return false;
    }
}
