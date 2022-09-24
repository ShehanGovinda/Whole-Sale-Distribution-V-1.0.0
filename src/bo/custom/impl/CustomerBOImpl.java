package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer customer : all) {
            allCustomers.add(new Customer(customer.getCustomerID(), customer.getCustomerName(), customer.getCustomerAddress(),customer.getPostalCode(),customer.getNationalId(),customer.getCity(),customer.getProvince(),customer.getCustomerTitle()));
        }
        return allCustomers;
    }

    @Override
    public boolean addCustomer(Customer customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(customerDTO.getCustomerID(), customerDTO.getCustomerName(), customerDTO.getCustomerAddress(),customerDTO.getPostalCode(),customerDTO.getNationalId(),customerDTO.getCity(),customerDTO.getProvince(),customerDTO.getCustomerTitle()));
    }

    @Override
    public boolean updateCustomer(Customer customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCustomerID(), customerDTO.getCustomerName(), customerDTO.getCustomerAddress(),customerDTO.getPostalCode(),customerDTO.getNationalId(),customerDTO.getCity(),customerDTO.getProvince(),customerDTO.getCustomerTitle()));
    }

    @Override
    public boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.ifCustomerExist(id);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return customerDAO.setCustomerIDS();
    }

    @Override
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomer(id);
    }
}
