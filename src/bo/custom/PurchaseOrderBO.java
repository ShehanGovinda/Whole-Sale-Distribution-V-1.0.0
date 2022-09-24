package bo.custom;

import bo.SuperBO;
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

public interface PurchaseOrderBO extends SuperBO {



    List<String> getAllItemDescriptions() throws SQLException, ClassNotFoundException;

    ArrayList<SavedOrder> getAllOrderIDSWithNIC() throws SQLException, ClassNotFoundException;

    ArrayList<SavedOrder> getNIC(String orderID) throws SQLException, ClassNotFoundException;

    Customer getCustomer(String nic) throws SQLException, ClassNotFoundException;

    Item search(String descript) throws SQLException, ClassNotFoundException;

    Item getItem(String code) throws SQLException, ClassNotFoundException;

    boolean deleteOrderFromSavedOrderTable(String orderID) throws SQLException, ClassNotFoundException;

    boolean saveOrderToDBTable(OrderDBtm o) throws SQLException, ClassNotFoundException;


    void updateItemTable(ObservableList<SavedOrderDetailsTM> obList) throws SQLException, ClassNotFoundException;

    void saveOrderToOrderDetailTable(String orderID, ObservableList<SavedOrderDetailsTM> obList) throws SQLException, ClassNotFoundException;

    ArrayList<SavedOrderDetailsTM> getOrderDetails(String newValue, Label lblCustNIC) throws SQLException, ClassNotFoundException;
    /*boolean ifItemExist(String id) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;*/

}
