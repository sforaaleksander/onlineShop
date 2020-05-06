package com.codecool.ui;

import java.util.Collection;

public class UI {
    private IO io;

    public UI() {
        io = new IO();
    }

    public void displayMainMenu(boolean loggedAsAdmin) {
        if (loggedAsAdmin) {
            displayAdminMainMenu();
        } else {
            displayCustomerMainMenu();
        }
    }

    public void abstractDisplayInnerMenu(String displayWhat) {
        print(new String[] { " Browse " + displayWhat + " Menu",
                             "(1) Show all " + displayWhat ,
                             "(2) Browse " + displayWhat + " by Id",
                             "(3) Browse " + displayWhat + " by name",
                             "(0) Exit" });
    }

    public void displayBrowseUsersMenu() {
        print(new String[] { " Browse Users Menu",
                             "(1) Show all users",
                             "(2) Browse users by Id",
                             "(3) Browse users by name",
                             "(0) Exit" });
    }

    private void displayCustomerMainMenu() {
        print(new String[] { " Main Menu                  Logged as Customer",
                             "(1) Display all products",
                             "(2) Browse by category",
                             "(3) Search for products by column",
                             "(0) Exit" });
    }

    private void displayBrowseProductsMenu() {
        print(new String[] { " Browse Products Menu",
                             "(1) Display all products",
                             "(2) Browse by category",
                             "(3) Search for products by column",
                             "(0) Exit" });
    }

    private void displayAdminMainMenu() {
        print(new String[] { " Main Menu                  Logged as Admin",
                             "(1) Browse products",
                             "(2) Browse users",
                             "(3) Browse orders",
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
