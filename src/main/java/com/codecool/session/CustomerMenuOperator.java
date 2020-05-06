package com.codecool.session;

import java.util.HashMap;
import java.util.Map;

import com.codecool.models.User;
import com.codecool.ui.UI;

public class CustomerMenuOperator extends MenuOperator {
    private Map<String, Runnable> mainMenuMap;

    public CustomerMenuOperator(User user, UI ui) {
        super(user, ui);
        createMainMenuMap();
    }

    private void createMainMenuMap() {
        mainMenuMap = new HashMap<>();
        mainMenuMap.put("1", this::customerProfileDetails);
        mainMenuMap.put("2", this::getOrdersByUserId);
        mainMenuMap.put("3", this::browseProducts);
        // mainMenuMap.put("c", this::openCart);
        mainMenuMap.put("0", this::exitProgram);
    }

    private void customerProfileDetails() {
        // TODO profile defails - with possibility of edition?
    }

    public Map<String, Runnable> getMainMenuMap() {
        return mainMenuMap;
    }

    public void setMainMenuMap(Map<String, Runnable> mainMenuMap) {
        this.mainMenuMap = mainMenuMap;
    }

    public UI getUi() {
        return ui;
    }
}
