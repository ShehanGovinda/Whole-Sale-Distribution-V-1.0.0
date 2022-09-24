package bo.custom.impl;

import bo.custom.SaveOrderBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.SavedOrderDAO;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.*;
import tm.SavedOrderDetailsTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaveOrderBOImpl implements SaveOrderBO {


    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final SavedOrderDAO savedOrderDAO = (SavedOrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.saveorder);


    @Override
    public List<String> getAllItemDescriptions() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllItemDescriptions();
    }

    @Override
    public Item search(String descript) throws SQLException, ClassNotFoundException {
        return itemDAO.search(descript);
    }

    @Override
    public Customer getCustomer(ArrayList<SavedOrder> nic) throws SQLException, ClassNotFoundException {
        return null;
    }

    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException {
        return savedOrderDAO.getCustomer(id);
    }


    @Override
    public boolean update(Customer c) throws SQLException, ClassNotFoundException {
       return customerDAO.update(c);
    }

    @Override
    public Item getItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.getItem(code);
    }

    @Override
    public boolean saveOrder(String orderId, String custNIC, ArrayList<ItemDetails> items) throws SQLException, ClassNotFoundException {
        return savedOrderDAO.saveOrder(orderId, custNIC, items);
    }



    @Override
    public boolean saveIDTOTempTable(String orderID) throws SQLException, ClassNotFoundException {
        return savedOrderDAO.saveIDTOTempTable(orderID);
    }


    @Override
    public String setCustomerIDS() throws SQLException, ClassNotFoundException {
        return customerDAO.setCustomerIDS();
    }


    @Override
    public String setOrderIDS() throws SQLException, ClassNotFoundException {
        return savedOrderDAO.setOrderIDS();
    }

    @Override
    public boolean add(Customer c) throws SQLException, ClassNotFoundException {
        return customerDAO.add(c);
    }

    @Override
    public ArrayList<SavedOrder> getAllOrderIDSWithNIC() throws SQLException, ClassNotFoundException{
        return savedOrderDAO.getAllOrderIDSWithNIC();
    }

    @Override
    public ArrayList<SavedOrder> getNIC(String orderID) throws SQLException, ClassNotFoundException {
        return savedOrderDAO.getNIC(orderID);
    }

    @Override
    public ArrayList<SavedOrderDetailsTM> getOrderDetails(String newValue, Label lblCustNIC) throws SQLException, ClassNotFoundException {
        return savedOrderDAO.getOrderDetails(newValue,lblCustNIC);
    }

    @Override
    public boolean saveOrdertoDbTable(ItemPackage o) throws SQLException, ClassNotFoundException {
        return savedOrderDAO.saveOrdertoDbTable(o);
    }

    @Override
    public void updateItemTable(ObservableList<SavedOrderDetailsTM> obList) throws SQLException, ClassNotFoundException {
        savedOrderDAO.updateItemTable(obList);
    }

    @Override
    public boolean deleteOrderFromSavedOrderTable(String orderID) throws SQLException, ClassNotFoundException {
        return savedOrderDAO.deleteOrderFromSavedOrderTable(orderID);
    }


}
