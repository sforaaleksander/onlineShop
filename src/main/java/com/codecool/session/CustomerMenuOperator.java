package com.codecool.session;


import com.codecool.models.User;
import com.codecool.ui.UI;

public class CustomerMenuOperator extends MenuOperator {

    public CustomerMenuOperator(User user, UI ui) {
        super(user, ui);
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

    private void addToCart(){
        System.out.println("ADDING TO CART");

    }
}
