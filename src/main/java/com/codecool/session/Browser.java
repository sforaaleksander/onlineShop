package com.codecool.session;

import com.codecool.dao.ProductDao;
import com.codecool.models.Product;
import com.codecool.ui.UI;

import java.util.List;

public class Browser {
    private final UI ui;
    private final ProductDao productDao;
    private String type;
    private String input;

    public Browser() {
        ui = new UI();
        productDao = new ProductDao();
    }

    public List<Product> browse() {
        getInputsForBrowsing();
        List<Product> productList = productDao.getProducts("WHERE " + type + " LIKE '%" + input + "%';");
        ui.printFlipTable(productList, Product.class);
        return productList;
    }

    private void getInputsForBrowsing() {
        String byWhatToFind = ui.gatherInput("By what do you want to search?\n1. Name\n2. Category");
        type = byWhatToFind.equals("1") ? "name" : "Id_category";
        input = byWhatToFind.equals("1") ? ui.gatherInput("What are you looking for?") : ui.gatherInput("1. Food\n2. Electronics");
    }
}
