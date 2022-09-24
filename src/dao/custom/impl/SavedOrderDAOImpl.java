package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.SavedOrderDAO;
import db.DbConnection;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.*;
import tm.OrderDBtm;
import tm.SavedOrderDetailsTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SavedOrderDAOImpl implements SavedOrderDAO {

    @Override
    public String setOrderIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT oId FROM TempOrderID ORDER BY oId DESC LIMIT 1");
        //ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT oId FROM TempOrderID ORDER BY oId DESC LIMIT 1").executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "O-00"+tempId;
            }else if(tempId<=99){
                return "O-0"+tempId;
            }else{
                return "O-"+tempId;
            }

        }else{
            return "O-001";
        }
    }

    @Override
    public boolean deleteOrderFromSavedOrderTable(String orderID) throws SQLException, ClassNotFoundException {

        return CrudUtil.executeUpdate("DELETE FROM `SavedOrder` WHERE oId=?", orderID);
//        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `SavedOrder` WHERE oId='"+orderID+"'").executeUpdate()>0){
//            return true;
//        }else{
//            return false;
//        }
    }

    @Override
    public boolean saveOrder(String orderId, String custNIC, ArrayList<ItemDetails> item) throws SQLException, ClassNotFoundException {
        //public boolean saveOrder(Item i) throws SQLException, ClassNotFoundException {
       // ArrayList<ItemDetails>allItems = new ArrayList<>();
        int count=0;

        for(ItemDetails temp : item) {
            return CrudUtil.executeUpdate("INSERT INTO `SavedOrder` VALUES(?,?,?,?,?,?,?)",orderId,custNIC,temp.getItemCode(),temp.getDescription(),temp.getQtyForSell(),temp.getDiscount(),temp.getTotal());
//            Connection con = DbConnection.getInstance().getConnection();
//            String query = "INSERT INTO `SavedOrder` VALUES(?,?,?,?,?,?,?)";
//            PreparedStatement stm = con.prepareStatement(query);
//            stm.setObject(1, orderId);
//            stm.setObject(2, custNIC);
//            stm.setObject(3, temp.getItemCode());
//            stm.setObject(4, temp.getDescription());
//            stm.setObject(5, temp.getQtyForSell());
//            stm.setObject(6, temp.getDiscount());
//            stm.setObject(7, temp.getTotal());
//            stm.executeUpdate();
//            count++;
        }
        if(count!=0){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<SavedOrder> getAllOrderIDSWithNIC() throws SQLException, ClassNotFoundException {

//        ArrayList<SavedOrder> allItems = new ArrayList<>();

//        while (rst.next()) {
//            allItems.add(new SavedOrder(rst.getString("oId"), rst.getString("NIC")));
//        }
//        return allItems;


//        PreparedStatement stm = DbConnection.getInstance()
//                .getConnection().prepareStatement("SELECT * FROM `SavedOrder`");
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `SavedOrder`");
        ArrayList<SavedOrder> temp = new ArrayList<>();

        // ResultSet rst = stm.executeQuery();
        while(rst.next()) {
            SavedOrder o =new SavedOrder(
                    rst.getString(1),
                    rst.getString(2)
            );
            temp.add(o);
        }
        return temp;
    }

    @Override
    public ArrayList<SavedOrder> getNIC(String orderID) throws SQLException, ClassNotFoundException {
        ArrayList<SavedOrder> NIC = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM savedorder WHERE oId='"+orderID+"'");
//        PreparedStatement stm = DbConnection.getInstance()
//                .getConnection().prepareStatement("SELECT * FROM savedorder WHERE oId='"+orderID+"'");
//
//        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            SavedOrder s=new SavedOrder(
                    rst.getString(2)
            );
        }
        return NIC;
    }

    @Override
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException {

        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE nationalId='"+id+"'");
//        PreparedStatement stm = DbConnection.getInstance()
//                .getConnection().prepareStatement("SELECT * FROM Customer WHERE nationalId='"+id+"'");


       // ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            //Customer customer = new Customer(
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
    public ArrayList<SavedOrderDetailsTM> getOrderDetails(String newValue, Label lblCustNIC) throws SQLException, ClassNotFoundException {

//        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM  `savedorder` WHERE oId=?", newValue);
//        rst.next();
//        return new SavedOrderDetailsTM(rst.getString(3),rst.getString(4),rst.getInt(5),rst.getDouble(6),rst.getDouble(7)));
//            lblCustNIC.setText(rst.getString(2));

        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM  `savedorder` WHERE oId='"+newValue+"'");

        ResultSet rst = stm.executeQuery();
        ArrayList<SavedOrderDetailsTM> items = new ArrayList<>();
        while (rst.next()){
            items.add(new SavedOrderDetailsTM(rst.getString(3),rst.getString(4),rst.getInt(5),rst.getDouble(6),rst.getDouble(7)));
            lblCustNIC.setText(rst.getString(2));
        }
        return items;
    }

    public boolean saveOrderByObject(String oID,String custNIC,SavedOrderDetailsTM o) throws SQLException, ClassNotFoundException {

        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO `SavedOrder` VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, oID);
        stm.setObject(2, custNIC);
        stm.setObject(3, o.getItemCode());
        stm.setObject(4, o.getDescription());
        stm.setObject(5, o.getQtyForSell());
        stm.setObject(6, o.getDiscount());
        stm.setObject(7, o.getTotal());
        return stm.executeUpdate()>0;
    }

    public boolean updateSavedOrder(String cNIC,String iCode,int q,double d,double t) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE `SavedOrder` SET quantity=?, discount=?, total=?  WHERE custNIC='"+cNIC+"' AND itemCode='"+iCode+"'");
        stm.setObject(5,q);
        stm.setObject(6,d);
        stm.setObject(7,t);
        //System.out.println(stm.executeUpdate()>0);
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean saveOrdertoDbTable(ItemPackage o) throws SQLException, ClassNotFoundException {

        return CrudUtil.executeUpdate("INSERT INTO `Order` VALUES(?,?,?,?)",o.getOrderID(),o.getOrderDate(),o.getcID(),o.getCost());

//        Connection con = DbConnection.getInstance().getConnection();
//        String query = "INSERT INTO `Order` VALUES(?,?,?,?)";
//        PreparedStatement stm = con.prepareStatement(query);
//        stm.setObject(1, o.getOrderID());
//        stm.setObject(2, o.getOrderDate());
//        stm.setObject(3, o.getcID());
//        stm.setObject(4, o.getCost());
//        return stm.executeUpdate() > 0;
    }

    public boolean saveOrderToDBTable(OrderDBtm o) throws SQLException, ClassNotFoundException {

        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO `Order` VALUES(?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, o.getoID());
        stm.setObject(2, o.getoDate());
        stm.setObject(3, o.getTime());
        stm.setObject(4, o.getcID());
        stm.setObject(5, o.getTotDiscount());
        stm.setObject(6, o.getTotPrice());
        return stm.executeUpdate() > 0;
    }

    @Override
    public void updateItemTable(ObservableList<SavedOrderDetailsTM> obList) throws SQLException, ClassNotFoundException {


        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE `Item` SET qtyOnHand=?  WHERE itemCode=?");
        for(SavedOrderDetailsTM s : obList) {

            Item i =new ItemDAOImpl().getItem(s.getItemCode());
            int qty = i.getQtyOnHand() - s.getQtyForSell();
            stm.setObject(1,qty);
            stm.setObject(2,s.getItemCode());

            stm.executeUpdate();
        }
    }

    @Override
    public boolean saveIDTOTempTable(String orderID) throws SQLException, ClassNotFoundException {

        return CrudUtil.executeUpdate("INSERT INTO `TempOrderID` VALUES(?)",orderID);
//        Connection con = DbConnection.getInstance().getConnection();
//        String query = "INSERT INTO `TempOrderID` VALUES(?)";
//        PreparedStatement stm = con.prepareStatement(query);
//        stm.setObject(1, orderID);
//        stm.executeUpdate();
    }

    public ArrayList<String> getYears() throws SQLException, ClassNotFoundException {
        ArrayList<String> year = new ArrayList<>();
//        PreparedStatement stm = DbConnection.getInstance()
//                .getConnection().prepareStatement("SELECT YEAR(orderDate) FROM `order`");
//
//        ResultSet rst = stm.executeQuery();
        ResultSet rst = CrudUtil.executeQuery("SELECT YEAR(orderDate) FROM `order`");
        while (rst.next()) {
            if(isYearExists(rst.getString(1),year)){

            }else {
                year.add(rst.getString(1));
            }
        }
        return year;
    }

    @Override
    public boolean isYearExists(String string, ArrayList<String> year){
        for(String y : year){
            if(y.equals(string)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getMonth() throws SQLException, ClassNotFoundException {
        ArrayList<String> year = new ArrayList<>();
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT MONTHNAME(orderDate) FROM `order`");

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            year.add(rst.getString(1));
        }
        return year;
    }

    public ArrayList<String> getDay() throws SQLException, ClassNotFoundException {
        ArrayList<String> year = new ArrayList<>();
//        PreparedStatement stm = DbConnection.getInstance()
//                .getConnection().prepareStatement("SELECT DAY(orderDate) FROM `order`");
//
//        ResultSet rst = stm.executeQuery();
        ResultSet rst = CrudUtil.executeQuery("SELECT DAY(orderDate) FROM `order`");
        if (rst.next()) {
            year.add(rst.getString(1));
        }
        return year;
    }

    @Override
    public String getItemDescriptionToLable(String iCode) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT description FROM Item WHERE itemCode='"+iCode+"'");
        //stm.setObject(1,iCode);
        ResultSet rst = stm.executeQuery();
        //ResultSet rst = CrudUtil.executeQuery("SELECT description FROM Item WHERE itemCode='"+iCode+"'");

        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    public void getMostMovableItem(Label lblMostMovableItem) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT iCode,COUNT(iCode) FROM `Order Detail` GROUP BY iCode ORDER BY iCode DESC LIMIT 1");
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {

            String iCode = rst.getString(1);
            String itemDescription = getItemDescriptionToLable(iCode);
            lblMostMovableItem.setText(itemDescription);

        } else {
            lblMostMovableItem.setText(" ");
        }
    }

    public void getLeastMovableItem(Label lblLeastMovableItem) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT iCode,COUNT(iCode) FROM `Order Detail` GROUP BY iCode ORDER BY iCode ASC LIMIT 1");
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {

            String iCode = rst.getString(1);
            String itemDescription = getItemDescriptionToLable(iCode);
            lblLeastMovableItem.setText(itemDescription);

        } else {
            lblLeastMovableItem.setText(" ");
        }
    }

    @Override
    public boolean add(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean add(SavedOrder savedOrder) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SavedOrder savedOrder) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public SavedOrder search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<SavedOrder> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }



}
