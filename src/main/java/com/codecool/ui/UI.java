package com.codecool.ui;

import java.util.Collection;

public class UI {
    private IO io;

    public UI() {
        io = new IO();
    }

    public void displayMenu(boolean loggedAsAdmin) {
        if (loggedAsAdmin) {
            displayAdminMenu();
        } else {
            displayCustomerMenu();
        }
    }

    public void displayBrowseUsersMenu() {
        print(new String[] { "    Browse users menu",
                             "(1) printAllUsers",
                             "(2) printuUsersByUserId",
                             "(3) printUsersContaining",
                             "(0) Exit" });
    }

    private void displayCustomerMenu() {
        print(new String[] { "    Admin menu",
                             "(1) Display all products",
                             "(2) Browse by category",
                             "(3) Search for products by column",
                             "(0) Exit" });
    }

    private void displayAdminMenu() {
        print(new String[] { "    Admin menu",
                             "(1) browseProducts",
                             "(2) browseUsers",
                             "(3) browseOrders",
                             "(0) Exit" });
    }

    public <T> void printTable(Iterable<T> rows, Class<T> rowType) {
        System.out.println(TableSupport.fromIterable(rows, rowType));
    }

    public void print(Collection<String> collection) {
        collection.forEach(System.out::println);
    }

    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    public void print(String[] toPrint) {
        for (String string : toPrint) {
            System.out.println(string);
        }
    }

    public void gatherEmptyInput(String message) {
        io.gatherEmptyInput(message);
    }

    public String gatherInput(String message) {
        return io.gatherInput(message);
    }

    public int gatherIntInput(String message, int rangeMin, int rangeMax) {
        return io.gatherIntInput(message, rangeMin, rangeMax);
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
    }
}
