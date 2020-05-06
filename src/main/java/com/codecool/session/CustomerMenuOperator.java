package com.codecool.session;

import com.codecool.models.Cart;
import com.codecool.models.Product;
import com.codecool.models.User;
import com.codecool.ui.UI;

import java.util.List;

import com.codecool.dao.ProductDao;

public class CustomerMenuOperator extends MenuOperator {
    private Cart cart;

    public CustomerMenuOperator(User user, UI ui) {
        super(user, ui);
        this.cart = new Cart();
        createMainMenuMap();
        productsMenuMap.put("4", this::addToCart);
        productsMenuMap.put("9", this::openCart);
    }

    private void createMainMenuMap() {
        mainMenuMap.put("1", this::customerProfileDetails);
        mainMenuMap.put("2", this::getOrdersByUserId);
        mainMenuMap.put("3", this::browseProducts);
        mainMenuMap.put("9", this::openCart);
        mainMenuMap.put("0", this::exitProgram);
    }

    private void customerProfileDetails() {
        // TODO profile defails - with possibility of edition?
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

    private void openCart(){
        ui.printTable(cart.getProducts(), Product.class);
    }

}
