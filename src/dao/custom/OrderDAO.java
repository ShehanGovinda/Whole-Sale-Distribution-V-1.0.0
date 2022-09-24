package dao.custom;

import dao.CrudDAO;
import model.*;
import tm.SavedOrderDetailsTM;
import dao.SuperDAO;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO extends CrudDAO<Order, String> {


    //public void saveOrderToOrderDetailTable(String orderID , ObservableList<SavedOrderDetailsTM> obList) throws SQLException, ClassNotFoundException {
    boolean saveOrderToOrderDetailTable(String orderID, ObservableList<SavedOrderDetailsTM> obList) throws SQLException, ClassNotFoundException;
}
