package com.codecool.onlineshop.session;

import com.codecool.onlineshop.models.Cart;
import com.codecool.onlineshop.models.Order;
import com.codecool.onlineshop.models.OrderStatus;
import com.codecool.onlineshop.models.Product;
import com.codecool.onlineshop.models.User;
import com.codecool.onlineshop.ui.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codecool.onlineshop.dao.OrderDao;
import com.codecool.onlineshop.dao.ProductDao;

public class CustomerMenuOperator extends MenuOperator {
    private final Cart cart;
    private Map<String, Runnable> cartMenuMap;
    final OrderDao orderDao;
    final ProductDao productDao;

    public CustomerMenuOperator(User user, UI ui) {
        super(user, ui);
        orderDao = new OrderDao();
        productDao = new ProductDao();
        cart = new Cart();
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
        cartMenuMap = new HashMap<>();
        cartMenuMap.put("1", this::order);
        cartMenuMap.put("2", this::payment);
        cartMenuMap.put("3", this::editCart);
    }

    private void order() {
        System.out.println("ORDERING");
        String idCustomer = Integer.toString(user.getId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String now = LocalDateTime.now().format(formatter);
        String createdAt = now.replace(" ", "T");

        String paidAt = "Waiting for payment";
        String orderStatus = OrderStatus.UNPAID.toString();

        String[] values = { idCustomer, createdAt, paidAt, orderStatus };

        orderDao.insertOrder(values);
        List<Product> productList = unpackCartToArrayList();
        String orderId = String.valueOf(getLastUserOrder().getId());
        for (Product product : productList) {
            String[] orderProductValues = { orderId, String.valueOf(product.getId()) };
            orderDao.insertOrderProducts(orderProductValues);
        }
        this.cart.emptyCart();
    }

    private Order getLastUserOrder() {
        List<Order> lastOrder = orderDao
                .getOrders("SELECT * FROM Orders WHERE Id_customer = "
                           + user.getId() + " ORDER BY Id DESC LIMIT 1;");
        return lastOrder.get(0);
    }

    private void payment() {
        System.out.println("PAYMENT");
        List<Order> unpaidOrders = getUnpaidOrders();
        if (unpaidOrders.isEmpty()) {
            ui.gatherEmptyInput("No unpaid orders");
            return;
        }
        displayUnpaidOrders();
        String orderId = ui.gatherInput("Provide order ID you want to pay for: ");
        if (!isOrderUnpaid(orderId, unpaidOrders)) {
            ui.gatherEmptyInput("Order of given ID not found");
            return;
        }
        ui.gatherEmptyInput("Enter credit card number");
        ui.gatherEmptyInput("Enter CVV");
        ui.gatherEmptyInput("Enter expiration date");
        ui.gatherEmptyInput("Payment processing, please wait...");
        ui.gatherEmptyInput("Payment received");
        String paidAt = LocalDateTime.now().toString().substring(0, 19);
        orderDao.updateOrder(orderId, "Paid_at", paidAt);
        orderDao.updateOrder(orderId, "Order_status", OrderStatus.PAID.toString());
    }

    private boolean isOrderUnpaid(String orderId, List<Order> unpaidOrders) {
        return unpaidOrders
                .stream()
                .anyMatch(order -> String.valueOf(order.getId()).equals(orderId));
    }

    private void displayUnpaidOrders() {
        orderDao.print("*", "Id_customer = " + user.getId() + " AND Order_status = 'UNPAID';");
    }

    private List<Order> getUnpaidOrders(){
        return orderDao.getOrders("SELECT * FROM Orders WHERE Id_customer = "
                                  + user.getId() + " AND Order_status = 'UNPAID';");
    }

    private void editCart() {
        System.out.println("EDITING CART");
        int productId = ui.gatherIntInput("Provide product ID which amount you want to change: ");
        int productAmount = ui.gatherIntInput("Provide a new amount of given product: ");
        for (Product product : cart.getProducts().keySet()) {
            if (product.getId() == productId) {
                cart.editCart(product, productAmount);
                cart.clearWhenZeroProducts();
                return;
            }
        }
        ui.gatherEmptyInput("Could not find product for given id ");
    }

    private List<Product> unpackCartToArrayList() {
        List<Product> cartList = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                cartList.add(entry.getKey());
            }
        }
        return cartList;
    }

    private void addToCart() {
        System.out.println("ADDING TO CART");
        String productId = ui.gatherInput("Provide product ID to add to cart: ");
        int productAmount = ui.gatherIntInput("Provide an amount of given product: ");

        // TODO abstract to outer method

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
        ui.printCart(cart.getProducts());
        handleMenu(cartMenuMap, ui::displayCartMenu);
    }
}
