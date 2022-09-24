package bo.custom;

import bo.SuperBO;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.*;
import tm.SavedOrderDetailsTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SaveOrderBO extends SuperBO {

    List<String> getAllItemDescriptions() throws SQLException, ClassNotFoundException;

    Item search(String descript) throws SQLException, ClassNotFoundException;

    Customer getCustomer(ArrayList<SavedOrder> nic) throws SQLException, ClassNotFoundException;

    boolean update(Customer c) throws SQLException, ClassNotFoundException;

    Item getItem(String code) throws SQLException, ClassNotFoundException;

    Customer getCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean saveOrder(String orderId, String custNIC, ArrayList<ItemDetails> items) throws SQLException, ClassNotFoundException;

    boolean saveIDTOTempTable(String orderID) throws SQLException, ClassNotFoundException;

    String setCustomerIDS() throws SQLException, ClassNotFoundException;

    String setOrderIDS() throws SQLException, ClassNotFoundException;

    boolean add(Customer c) throws SQLException, ClassNotFoundException;

    ArrayList<SavedOrder> getAllOrderIDSWithNIC() throws SQLException, ClassNotFoundException;

    ArrayList<SavedOrder> getNIC(String orderID) throws SQLException, ClassNotFoundException;

    ArrayList<SavedOrderDetailsTM> getOrderDetails(String newValue, Label lblCustNIC) throws SQLException, ClassNotFoundException;

    boolean saveOrdertoDbTable(ItemPackage o) throws SQLException, ClassNotFoundException;

    void updateItemTable(ObservableList<SavedOrderDetailsTM> obList) throws SQLException, ClassNotFoundException;

    boolean deleteOrderFromSavedOrderTable(String orderID) throws SQLException, ClassNotFoundException;
}
