package com.codecool.session;

import java.util.List;

import com.codecool.dao.OrderDao;
import com.codecool.models.Admin;
import com.codecool.models.Order;
import com.codecool.ui.UI;

public abstract class AdminMenuOperator extends MenuOperator {
    private Admin admin;

    AdminMenuOperator(Admin admin, UI ui) {
        super(ui);
        this.admin = admin;
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
}