package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.*;
import tm.SavedOrderDetailsTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SavedOrderDAO extends CrudDAO<SavedOrder, String> {

    String setOrderIDS() throws SQLException, ClassNotFoundException;

    boolean deleteOrderFromSavedOrderTable(String orderID) throws SQLException, ClassNotFoundException;

    boolean saveOrder(String orderId, String custNIC, ArrayList<ItemDetails> item) throws SQLException, ClassNotFoundException;

    ArrayList<SavedOrder> getAllOrderIDSWithNIC() throws SQLException, ClassNotFoundException;

    ArrayList<SavedOrder> getNIC(String orderID) throws SQLException, ClassNotFoundException;

    Customer getCustomer(String id) throws SQLException, ClassNotFoundException;

    ArrayList<SavedOrderDetailsTM> getOrderDetails(String newValue, Label lblCustNIC) throws SQLException, ClassNotFoundException;

    boolean saveOrdertoDbTable(ItemPackage o) throws SQLException, ClassNotFoundException;

    void updateItemTable(ObservableList<SavedOrderDetailsTM> obList) throws SQLException, ClassNotFoundException;

    boolean saveIDTOTempTable(String orderID) throws SQLException, ClassNotFoundException;

    boolean isYearExists(String string, ArrayList<String> year);

    String getItemDescriptionToLable(String iCode) throws SQLException, ClassNotFoundException;

    boolean add(Order order) throws SQLException, ClassNotFoundException;
}
