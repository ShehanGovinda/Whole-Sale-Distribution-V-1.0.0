package bo.custom;

import bo.SuperBO;
import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean addCustomer(Customer customerDTO) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(Customer customerDTO) throws SQLException, ClassNotFoundException;

    boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    Customer getCustomer(String id) throws SQLException, ClassNotFoundException;
}
