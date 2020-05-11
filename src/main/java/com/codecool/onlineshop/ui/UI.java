package com.codecool.onlineshop.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.codecool.onlineshop.models.Product;
import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;

public class UI {
    private final IO io;

    private enum Messages {
        BROWSE_ORDERS(new String[] { " Browse Orders Menu",
                "(1) Show all Orders",
                "(2) Browse Orders by id",
                "(3) Search for orders by column",
                "(0) Back" });

        private final String[] message;

        Messages(String[] message) {
            this.message = message;
        }
    }
    public UI() {
        io = new IO();
    }

    public void displayBrowseOrdersMenu() {
        print(Messages.BROWSE_ORDERS.message);
    }

    public void displayUserProfileMenu() {
        print(new String[] { " Browse Orders Menu",
                             "(1) Edit user details",
                             "(0) Back" });
    }

    public void displayBrowseUsersMenu() {
        print(new String[] { " Browse Users Menu",
                             "(1) Show all users",
                             "(2) Browse users by id",
                             "(3) Browse users by column",
                             "(4) Remove user",
                             "(0) Back" });
    }

    public void displayCustomerBrowseProductsMenu() {
        print(new String[] { " Browse Products Menu",
                             "(1) Display all products",
                             "(2) Browse by category",
                             "(3) Search for products by column",
                             "(4) Add to cart by the product's ID",
                             "(9) Open Cart",
                             "(0) Back" });
    }

    public void displayAdminBrowseProductsMenu() {
        print(new String[] { " Browse Products Menu",
                             "(1) Display all products",
                             "(2) Browse by category",
                             "(3) Search for products by column",
                             "(4) Add new product",
                             "(5) Edit product", 
                             "(6) Remove product", 
                             "(7) Display all categories",
                             "(8) Add new category",
                             "(0) Back" });
    }

    public void displayCartMenu() {
        print(new String[] { " Your Cart",
                             "(1) Order",
                             "(2) Pay for order",
                             "(3) Edit cart",
                             "(0) Back"});
    }

    public void displayCustomerMainMenu() {
        print(new String[] { " Main Menu     Logged as Customer",
                             "(1) View profile",
                             "(2) View order history",
                             "(3) Browse products",
                             "(9) Open Cart",
                             "(0) Exit" });
    }

    public void displayAdminMainMenu() {
        print(new String[] { " Main Menu     Logged as Admin",
                             "(1) Browse products",
                             "(2) Browse users",
                             "(3) Browse orders",
                             "(0) Exit" });
    }

    public void displayLoginOrRegistrationMenu() {
        print(new String[] {"Welcome to OnlineShop",
                             "(1) Login",
                             "(2) Register"});
    }

    public <T> void printTable(Iterable<T> rows, Class<T> rowType) {
        System.out.println(TableSupport.fromIterable(rows, rowType));
    }

    public void printCart(Map<Product, Integer> map) {
        String[][] data = new String[map.size()][3];
        int i = 0;
        for (Map.Entry<Product, Integer> entry : map.entrySet()) {
            data[i][0] = entry.getKey().getId() + "";
            data[i][1] = entry.getKey().getName();
            data[i++][2] = entry.getValue() + "";
        }
        String[] headers = { "Id", "Product name", "Quantity" };
        System.out.println(FlipTable.of(headers, data));
    }

    public void printTableFromDB(ResultSet resultSet) throws SQLException {
        System.out.println(FlipTableConverters.fromResultSet(resultSet));
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

    public int gatherIntInput(String message) {
        return io.gatherIntInput(message);
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
    }
}
