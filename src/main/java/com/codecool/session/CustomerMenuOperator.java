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
    }

    private void createMainMenuMap() {
        mainMenuMap.put("1", this::customerProfileDetails);
        mainMenuMap.put("2", this::getOrdersByUserId);
        mainMenuMap.put("3", this::browseProducts);
        mainMenuMap.put("c", this::openCart);
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

        // TODO abstract to outer method

        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getProducts("SELECT * FROM Products WHERE id =" + productId + ";");
        if (!products.isEmpty()) {
            this.cart.addToCart(products.get(0));
        } else {
            ui.gatherInput("No product found for given ID");
        }
    }
}
