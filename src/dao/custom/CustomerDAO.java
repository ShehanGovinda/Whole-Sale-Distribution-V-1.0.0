package dao.custom;

import dao.CrudDAO;
import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer, String> {


    Customer getCustomer(String id) throws SQLException, ClassNotFoundException;

    ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException;

    String setCustomerIDS() throws SQLException, ClassNotFoundException;

    boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException;
}
