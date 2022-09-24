package dao.custom;

import dao.CrudDAO;
import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item, String> {


    List<String> getAllItemDescriptions() throws SQLException, ClassNotFoundException;

    Item getItem(String id) throws SQLException, ClassNotFoundException;

    String getItemCode() throws SQLException, ClassNotFoundException;

    Item getItems(String id) throws SQLException, ClassNotFoundException;

    Item getItemForShow(String itemDesc) throws SQLException, ClassNotFoundException;

    Item getItemByDescription(String itemDesc) throws SQLException, ClassNotFoundException;

    boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException;
}
