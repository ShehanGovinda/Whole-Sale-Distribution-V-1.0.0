package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    //add Customers
    @Override
    public boolean add(Customer c) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?)",c.getCustomerID(),c.getCustomerName(),c.getCustomerAddress(),c.getPostalCode(),c.getNationalId(),c.getCity(),c.getProvince(),c.getCustomerTitle());


//        Connection con = DbConnection.getInstance().getConnection();
//        String query = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?)";
//        PreparedStatement stm = con.prepareStatement(query);
//        stm.setObject(1, c.getCustomerID());
//        stm.setObject(2, c.getCustomerName());
//        stm.setObject(3, c.getCustomerAddress());
//        stm.setObject(4, c.getPostalCode());
//        stm.setObject(5, c.getNationalId());
//        stm.setObject(6, c.getCity());
//        stm.setObject(7, c.getProvince());
//        stm.setObject(8, c.getCustomerTitle());
//        return stm.executeUpdate() > 0;

    }

    //update customers
    @Override
    public boolean update(Customer c) throws SQLException, ClassNotFoundException {

        return CrudUtil.executeUpdate("UPDATE Customer SET custID=?, custName=?, custAddress=?, postalCode=?,nationalId=?, city=?, province=?, custTitle=? WHERE nationalId='"+c.getNationalId()+"'", c.getCustomerID(),c.getCustomerName(),c.getCustomerAddress(),c.getPostalCode(),c.getNationalId(),c.getCity(),c.getProvince(),c.getCustomerTitle());

//        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET custID=?, custName=?, custAddress=?, postalCode=?, nationalId=?, city=?, province=?, custTitle=? WHERE nationalId='" + c.getNationalId() + "'");
//        stm.setObject(1, c.getCustomerID());
//        stm.setObject(2, c.getCustomerName());
//        stm.setObject(3, c.getCustomerAddress());
//        stm.setObject(4, c.getPostalCode());
//        stm.setObject(5, c.getNationalId());
//        stm.setObject(6, c.getCity());
//        stm.setObject(7, c.getProvince());
//        stm.setObject(8, c.getCustomerTitle());
//        return stm.executeUpdate() > 0;

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException {

        // return new Customer(rst.getString("custID"), rst.getString("custName"), rst.getString("custAddress"),rst.getString("postalCode"),rst.getString("nationalId"),rst.getString("city"),rst.getString("province"),rst.getString("custTitle"));
//        PreparedStatement stm = DbConnection.getInstance()
//                .getConnection().prepareStatement("SELECT * FROM Customer WHERE nationalId='" + id + "'");
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE nationalId='" + id + "'");
        rst.next();
//        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
            );

        } else {
            return null;
        }

    }

    @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String setCustomerIDS() throws SQLException, ClassNotFoundException {

        // ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT custID FROM Customer ORDER BY custID DESC LIMIT 1").executeQuery();
        ResultSet rst = CrudUtil.executeQuery("SELECT custID FROM Customer ORDER BY custID DESC LIMIT 1");
        if (rst.next()) {

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId + 1;
            if (tempId < 9) {
                return "C-00" + tempId;
            } else if (tempId < 99) {
                return "C-0" + tempId;
            } else {
                return "C-" + tempId;
            }

        } else {
            return "C-001";
        }
    }


    @Override
    public Customer search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }



    @Override
    public boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT custID FROM Customer WHERE custID=?", id).next();
    }

}
