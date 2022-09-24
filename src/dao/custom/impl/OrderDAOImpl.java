package dao.custom.impl;

import model.*;
import tm.OrderDBtm;
import tm.SavedOrderDetailsTM;
import dao.CrudUtil;
import dao.custom.OrderDAO;

import db.DbConnection;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {


     @Override
    public boolean saveOrderToOrderDetailTable(String orderID, ObservableList<SavedOrderDetailsTM> obList) throws SQLException, ClassNotFoundException {

        return CrudUtil.executeUpdate("INSERT INTO `Order Detail` VALUES(?,?,?,?,?)",orderID,obList);

//        try {
//            Connection con = DbConnection.getInstance().getConnection();
//            String query = "INSERT INTO `Order Detail` VALUES(?,?,?,?,?)";
//            PreparedStatement stm = con.prepareStatement(query);
//            for (SavedOrderDetailsTM o : obList) {
//                stm.setObject(1, orderID);
//                stm.setObject(2, o.getItemCode());
//                stm.setObject(3, o.getQtyForSell());
//                stm.setObject(4, o.getDiscount());
//                stm.setObject(5, o.getTotal());
//                stm.executeUpdate();
//            }
//
//        }catch (Exception e){}
//        //return stm.executeUpdate() > 0;
    }

    @Override
    public boolean add(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
