package model;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private String orderDate;
    private String customerNIC;
    private double cost;
    private ArrayList<ItemDetails> item;

    public Order() {
    }

    public Order(String orderId, String orderDate, String customerNIC, double cost, ArrayList<ItemDetails> item) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerNIC = customerNIC;
        this.cost = cost;
        this.item = item;
    }

    public Order(String orderId, String customerNIC, ArrayList<ItemDetails> item) {
        this.orderId = orderId;
        this.customerNIC = customerNIC;
        this.item = item;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerNIC() {
        return customerNIC;
    }

    public void setCustomerNIC(String customerNIC) {
        this.customerNIC = customerNIC;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<ItemDetails> getItem() {
        return item;
    }

    public void setItem(ArrayList<ItemDetails> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", customerNIC='" + customerNIC + '\'' +
                ", cost=" + cost +
                ", item=" + item +
                '}';
    }
}
