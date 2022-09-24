package bo.custom.impl;

import bo.custom.PurchaseOrderBO;
import dao.DAOFactory;
import dao.custom.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.Customer;
import model.Item;
import model.SavedOrder;
import tm.OrderDBtm;
import tm.SavedOrderDetailsTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final SavedOrderDAO savedOrderDAO = (SavedOrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.saveorder);

    @Override
    public List<String> getAllItemDescriptions() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllItemDescriptions();
    }

    @Override
    public ArrayList<SavedOrder> getAllOrderIDSWithNIC() throws SQLException, ClassNotFoundException {
        return savedOrderDAO.getAllOrderIDSWithNIC();
    }

    @Override
    public ArrayList<SavedOrder> getNIC(String orderID) throws SQLException, ClassNotFoundException {
        return savedOrderDAO.getNIC(orderID);
    }

    @Override
    public Customer getCustomer(String nic) throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomer(nic);
    }

    @Override
    public Item search(String descript) throws SQLException, ClassNotFoundException {
        return itemDAO.search(descript);
    }

    @Override
    public Item getItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.getItem(code);
    }

    @Override
    public boolean deleteOrderFromSavedOrderTable(String orderID) throws SQLException, ClassNotFoundException {
        return savedOrderDAO.deleteOrderFromSavedOrderTable(orderID);
    }

    @Override
    public boolean saveOrderToDBTable(OrderDBtm o) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void updateItemTable(ObservableList<SavedOrderDetailsTM> obList) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void saveOrderToOrderDetailTable(String orderID, ObservableList<SavedOrderDetailsTM> obList) throws SQLException, ClassNotFoundException {

    }



    @Override
    public ArrayList<SavedOrderDetailsTM> getOrderDetails(String newValue, Label lblCustNIC) throws SQLException, ClassNotFoundException {
        return savedOrderDAO.getOrderDetails(newValue,lblCustNIC);
    }
}
