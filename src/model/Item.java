package model;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;

public class Item {
    private String itemCode;
    private String packSize;
    private double unitPrice;
    private int qtyOnHand;
    private String description;
    private double discount;

    public Item() {
    }

    public Item(String itemCode, String packSize, double unitPrice, int qtyOnHand, String description, double discount) {
        this.itemCode = itemCode;
        this.packSize = packSize;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
        this.description = description;
        this.discount = discount;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemCode='" + itemCode + '\'' +
                ", packSize='" + packSize + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.unitPrice, unitPrice) == 0 &&
                qtyOnHand == item.qtyOnHand &&
                Double.compare(item.discount, discount) == 0 &&
                Objects.equals(itemCode, item.itemCode) &&
                Objects.equals(packSize, item.packSize) &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCode, packSize, unitPrice, qtyOnHand, description, discount);
    }
}