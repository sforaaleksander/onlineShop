package com.codecool.session;

import java.util.List;
import java.util.Map;

import com.codecool.dao.ProductDao;
import com.codecool.models.Product;
import com.codecool.ui.UI;

public abstract class MenuOperator {
    protected Map<String, Runnable> browseProductsMap;
    protected UI ui;

    MenuOperator(UI ui) {
        this.ui = ui;
        createBrowseProducts();
    }


    public UI getUi() {
        return ui;
    }  

    private void createBrowseProducts(){
        browseProductsMap.put("2", this::getAllProducts);
        browseProductsMap.put("3", this::getProductsByCategory);
        browseProductsMap.put("4", this::getProductsContaining);
        browseProductsMap.put("c", this::openCart);
    }

    public Map<String, Runnable> getBrowseProductsMap() {
        return browseProductsMap;
    }

    protected List<Product> getAllProducts() {
        return new ProductDao().getProducts("SELECT * FROM Products;");
    }

    protected List<Product> getProductsContaining() {
        String column;
        String toSearch;
        column = ui.gatherInput("Provide column: ");
        toSearch = ui.gatherInput("What to look for?: ");
        return new ProductDao().getProducts("SELECT * FROM Products WHERE " + column + " LIKE '%" + toSearch + "%';");
    }

    protected List<Product> getProductsByCategory() {
        String category;
        category = ui.gatherInput("Provide category: ");
        return new ProductDao().getProducts("SELECT * FROM Products JOIN Category ON Products.Id_category = Category.Id WHERE Category.Name = '" + category + "';");
    }

    protected void openCart(){

    }

    private void exitProgram() {

    }
    
}