package com.codecool.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codecool.dao.OrderDao;
import com.codecool.models.Order;
import com.codecool.models.User;
import com.codecool.ui.UI;

public class AdminMenuOperator extends MenuOperator {
    private Map<String, Runnable> mainMenuMap;
    private Map<String, Runnable> ordersMenuMap;

    public AdminMenuOperator(User user, UI ui) {
        super(user, ui);
        createMainMenuMap();
    }

    private void createMainMenuMap() {
        mainMenuMap = new HashMap<>();
        mainMenuMap.put("1", this::browseProducts);
        mainMenuMap.put("2", this::browseUsers);
        mainMenuMap.put("3", this::browseOrders);
    }

    private void browseOrders() {
        ordersMenuMap = new HashMap<>();
        ordersMenuMap.put("1", this::getAllOrders);
        ordersMenuMap.put("2", this::getOrdersByUserId);
        ordersMenuMap.put("3", this::getOrdersContaining);
        ordersMenuMap.put("0", this::exitProgram);
    }

    private void browseUsers() {
        // TODO
    }

    private List<Order> getAllOrders() {
        return new OrderDao().getOrders("SELECT * FROM Orders;");
    }

    private List<Order> getOrdersContaining() {
        String column;
        String toSearch;
        column = ui.gatherInput("Provide column: ");
        toSearch = ui.gatherInput("What to look for?: ");
        return new OrderDao().getOrders("SELECT Order_status, Created_at, Paid_at, Name, Price FROM Orders"
                                        + "JOIN Order_products ON Order_products.Id_order = Orders.Id JOIN Products ON"
                                        + "Products.Id = Order_products.Id_product WHERE " + column + " LIKE '%" + toSearch + "%';");
    }

    public Map<String, Runnable> getMainMenuMap() {
        return mainMenuMap;
    }
}
