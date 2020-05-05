package com.codecool.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codecool.models.Order;
import com.codecool.models.Product;
import com.codecool.models.Customer;
import com.codecool.ui.UI;
import com.codecool.dao.OrderDao;
import com.codecool.dao.ProductDao;

public class CustomerMenuOperator extends MenuOperator {
    private Customer customer;
    private Map<String, Runnable> mainMenuMap;

    CustomerMenuOperator(Customer customer, UI ui) {
        super(ui);
        this.customer = customer;
    }


    private void createMainMenuMap() {
        mainMenuMap = new HashMap<>();
        mainMenuMap.put("1", this::customerProfileDetails);
        mainMenuMap.put("2", this::customerHistoryOrder);
        mainMenuMap.put("3", this::browseProducts);
        // mainMenuMap.put("c", this::openCart);
        mainMenuMap.put("0", this::exitProgram);
    }

    private void browseProducts(){

    }

    private void customerProfileDetails(){
        // TODO profile defails - with possibility of edition?
    }

    private void customerHistoryOrder(){
        // TODO view previous orders
    }


    private void createAdminMainMenuMap() {
        mainMenuMap = new HashMap<>();
        mainMenuMap.put("1", this::getAllProducts);
        mainMenuMap.put("2", this::getProductsByCategory);
        mainMenuMap.put("3", this::getProductsContaining);
        mainMenuMap.put("4", this::getAllOrders);
        mainMenuMap.put("5", this::getOrdersByUserId);
        mainMenuMap.put("6", this::getOrdersContaining);
        mainMenuMap.put("0", this::exitProgram);
    }


    private List<Order> getOrdersByUserId() {
        String userId;
        userId = ui.gatherInput("Provide userId: ");
        return new OrderDao().getOrders("SELECT Order_status, Created_at, Paid_at, Name, Price FROM Orders"
                                        + "JOIN Order_products ON Order_products.Id_order = Orders.Id JOIN Products ON"
                                        + "Products.Id = Order_products.Id_product WHERE Orders.Id_customer = " + userId + ";");
    }

    private List<Order> getAllOrders() {
        return new OrderDao().getOrders("SELECT * FROM Orders;");
    }

    public Map<String, Runnable> getMainMenuMap() {
        return mainMenuMap;
    }

    public void setMainMenuMap(Map<String, Runnable> mainMenuMap) {
        this.mainMenuMap = mainMenuMap;
    }

    public Customer getCustomer() {
        return customer;
    }

    public UI getUi() {
        return ui;
    }

}
