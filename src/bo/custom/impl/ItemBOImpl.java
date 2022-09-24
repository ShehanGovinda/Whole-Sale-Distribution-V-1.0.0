package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItems = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item i : all) {
            allItems.add(new Item(i.getItemCode(),i.getPackSize(),i.getUnitPrice(),i.getQtyOnHand(),i.getDescription(),i.getDiscount()));
        }
        return allItems;
    }

    @Override
    public boolean addItem(Item itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(itemDTO.getItemCode(),itemDTO.getPackSize(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand(),itemDTO.getDescription(),itemDTO.getDiscount()));
    }

    @Override
    public boolean updateItem(Item itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(itemDTO.getItemCode(),itemDTO.getPackSize(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand(),itemDTO.getDescription(),itemDTO.getDiscount()));
    }

    @Override
    public boolean ifItemExist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }



    @Override
    public Item getItemCode(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.getItems(id);
    }



    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemCode();
    }

    @Override
    public  Item getItemByDescription(String itemDesc) throws SQLException, ClassNotFoundException {
        return itemDAO.getItemByDescription(itemDesc);
    }

    @Override
    public Item getItemForShow(String itemDesc) throws SQLException, ClassNotFoundException {
        return itemDAO.getItemForShow(itemDesc);
    }



}
