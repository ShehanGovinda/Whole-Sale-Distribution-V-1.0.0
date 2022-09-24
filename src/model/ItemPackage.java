package model;

import java.util.Date;

public class ItemPackage {
   private String orderID;
   private String orderDate;
    private String  cID;
    private String cost;

    public ItemPackage() {
    }

    public ItemPackage(String orderID, String orderDate, String cID, String cost) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.cID = cID;
        this.cost = cost;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "ItemPackage{" +
                "orderID='" + orderID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", cID='" + cID + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
