package model;

import java.util.Objects;

public class Customer {
    private String customerID;
    private String customerName;
    private String customerAddress;
    private String postalCode;
    private String nationalId;
    private String city;
    private String province;
    private String customerTitle;

    public Customer(String customerID, String customerName, String customerAddress, String postalCode, String nationalId, String city, String province, String customerTitle) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.postalCode = postalCode;
        this.nationalId = nationalId;
        this.city = city;
        this.province = province;
        this.customerTitle = customerTitle;
    }

    public Customer() {
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", customerTitle='" + customerTitle + '\'' +
                '}';
    }
}
