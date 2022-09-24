package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import db.DbConnection;
import model.Item;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
//    public List<String> getAllItemIds() throws SQLException, ClassNotFoundException {
//        ResultSet rst = DbConnection.getInstance().getConnection().
//                prepareStatement("SELECT * FROM Item").executeQuery();
//        List<String> ids= new ArrayList<>();
//        while (rst.next()){
//            ids.add(
//                    rst.getString(1)
//            );
//        }
//        return ids;
//    }

    @Override
    public List<String> getAllItemDescriptions() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Item").executeQuery();
        List<String> descriptions= new ArrayList<>();
        while (rst.next()){
            descriptions.add(
                    rst.getString(5)
            );
        }
        return descriptions;
    }


    @Override
    public boolean add(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?,?,?)",i.getItemCode(),i.getPackSize(),i.getUnitPrice(),i.getQtyOnHand(),i.getDescription(),i.getDiscount());
//        Connection con= DbConnection.getInstance().getConnection();
//        String query="INSERT INTO Item VALUES(?,?,?,?,?,?)";
//        PreparedStatement stm = con.prepareStatement(query);
//        stm.setObject(1,i.getItemCode());
//        stm.setObject(2,i.getPackSize());
//        stm.setObject(3,i.getUnitPrice());
//        stm.setObject(4,i.getQtyOnHand());
//        stm.setObject(5,i.getDescription());
//        stm.setObject(6,i.getDiscount());
//        return stm.executeUpdate()>0;
    }

    @Override
   public Item getItem(String id) throws SQLException, ClassNotFoundException {

       ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE itemCode='" + id + "'");
       rst.next();
       return new Item(rst.getString("itemCode"), rst.getString("packSize"), rst.getDouble("unitPrice"),rst.getInt("qtyOnHand"),rst.getString("description"),rst.getDouble("discount"));

//       ResultSet rst = DbConnection.getInstance().getConnection().
//                prepareStatement("SELECT * FROM Item WHERE itemCode='" + id + "'").
//                executeQuery();
//        if (rst.next()){
//            return new Item(
//                    rst.getString(1),
//                    rst.getString(2),
//                    rst.getDouble(3),
//                    rst.getInt(4),
//                    rst.getString(5),
//                    rst.getDouble(6)
//            );
//        }else{
//            return null;
//        }
    }

    @Override
   public String getItemCode() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT itemCode FROM `item` ORDER BY itemCode DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "I-00"+tempId;
            }else if(tempId<=99){
                return "I-0"+tempId;
            }else{
                return "I-"+tempId;
            }

        }else{
            return "I-001";
        }
    }



    @Override
    public boolean update(Item i) throws SQLException, ClassNotFoundException {

        return CrudUtil.executeUpdate("UPDATE Item SET  packSize=?, unitPrice=?, qtyOnHand=?, description=?, discount=? WHERE itemCode=?",i.getPackSize(),i.getUnitPrice(),i.getQtyOnHand(),i.getDescription(),i.getDiscount(),i.getItemCode());

//        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET  packSize=?, unitPrice=?, qtyOnHand=?, description=?, discount=? WHERE itemCode='"+i.getItemCode()+"'");
//        stm.setObject(1,i.getPackSize());
//        stm.setObject(2,i.getUnitPrice());
//        stm.setObject(3,i.getQtyOnHand());
//        stm.setObject(4,i.getDescription());
//        stm.setObject(5,i.getDiscount());
//        return stm.executeUpdate()>0;
    }

    @Override
    public boolean delete(String itemCode) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Item WHERE itemCode='"+itemCode+"'");
//        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE itemCode='"+itemCode+"'").executeUpdate()>0){
//            return true;
//        }else{
//            return false;
//        }
    }

    @Override
    public Item getItems(String id) throws SQLException, ClassNotFoundException {

        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE itemCode='" + id + "'");
//        while (rst.next()) {
//            allItems.add(new Item(rst.getString("itemCode"),rst.getString("packSize"), rst.getDouble("unitPrice"),  rst.getInt("qtyOnHand"),rst.getString("description"),rst.getDouble("discount")));
//        }
//        return allItems;


//        ResultSet rst = DbConnection.getInstance().getConnection().
//                prepareStatement("SELECT * FROM Item WHERE itemCode='" + id + "'").
//                executeQuery();
        if (rst.next()){
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getDouble(6)
            );
        }else{
            return null;
        }
    }

    @Override
    public Item getItemForShow(String itemDesc) throws SQLException, ClassNotFoundException {

        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE description = '" + itemDesc + "'");
//        ResultSet rst = DbConnection.getInstance().getConnection().
//                prepareStatement("SELECT * FROM Item WHERE description = '" + itemDesc + "'").
//                executeQuery();
        if (rst.next()){
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getDouble(6)
            );
        }else{
            return null;
        }
    }

    @Override
    public  Item getItemByDescription(String itemDesc) throws SQLException, ClassNotFoundException {
//        ResultSet rst = DbConnection.getInstance().getConnection().
//                prepareStatement("SELECT * FROM Item WHERE description ='" + itemDesc + "'").
//                executeQuery();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE description ='" + itemDesc + "'");
        if (rst.next()) {
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getDouble(6)
            );
        } else {
            return null;
        }
    }

    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT custID FROM Customer WHERE custID=?", id).next();
    }






}
