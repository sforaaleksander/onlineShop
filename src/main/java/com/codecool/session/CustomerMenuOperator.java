package com.codecool.session;

import com.codecool.models.Cart;
import com.codecool.models.Product;
import com.codecool.models.User;
import com.codecool.ui.UI;

import java.util.List;
import java.util.Map;

import com.codecool.dao.ProductDao;

public class CustomerMenuOperator extends MenuOperator {
    private Cart cart;
    private Map<String, Runnable> cartMenuMap;

    public CustomerMenuOperator(User user, UI ui) {
        super(user, ui);
        this.cart = new Cart();
        createMainMenuMap();
        createCartMenuMap();
        productsMenuMap.put("4", this::addToCart);
        productsMenuMap.put("9", this::openCart);
    }

    private void createMainMenuMap() {
        mainMenuMap.put("1", this::userProfile);
        mainMenuMap.put("2", this::getOrdersByUserId);
        mainMenuMap.put("3", this::browseProducts);
        mainMenuMap.put("9", this::openCart);
    }

    private void createCartMenuMap() {
        mainMenuMap.put("1", this::payment);
        mainMenuMap.put("2", this::editCart);
    }

    private void payment() {
    }

    private void editCart() {
        System.out.println("EDITING CART");
        int productId = ui.gatherIntInput("Provide product ID which amount you want to change: ");
        int productAmount = ui.gatherIntInput("Provide new amount of given product: ");
        for (Product product : cart.getProducts().keySet()) {
            if (product.getId() == productId) {
                cart.editCart(product, productAmount);
                cart.clearWhenZeroProducts();
            } else {
                ui.gatherInput("Could not find product for given id ");
            }
        }
    }

    public UI getUi() {
        return ui;
    }

    private void addToCart() {
        System.out.println("ADDING TO CART");
        String productId = ui.gatherInput("Provide product ID to add to cart: ");
        int productAmount = ui.gatherIntInput("Provide amount of given product: ");

        // TODO abstract to outer method

        ProductDao productDao = new ProductDao();
        List<Product> productsList = productDao.getProducts("SELECT * FROM Products WHERE id =" + productId + ";");

        if (productsList.isEmpty()) {
            ui.gatherEmptyInput("No product found for given ID");
            return;
        }

        if (productsList.get(0).getQuantity() < productAmount) {
            ui.gatherEmptyInput("Not enough products in stock");
            return;
        }

        for (int i = 0; i < productAmount; i++) {
            this.cart.addToCart(productsList.get(0));
        }
    }

    private void openCart() {
        // ui.printTable(cart.getProducts(), Product.class);
        handleMenu(cartMenuMap, ui::displayCartMenu);
    }

}
