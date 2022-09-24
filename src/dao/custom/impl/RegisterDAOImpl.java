package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.RegisterDAO;
import db.DbConnection;
import model.Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterDAOImpl implements RegisterDAO {

    @Override
    public boolean saveRegister(Register r) throws SQLException, ClassNotFoundException {

        return CrudUtil.executeUpdate("INSERT INTO Register VALUES(?,?,?,?,?,?,?)",r.getName(),r.getAddress(),r.getEmail(),r.getUserType(),r.getTelephoneNum(),r.getUserName(),r.getPassword());

//        Connection con= DbConnection.getInstance().getConnection();
//        String query="INSERT INTO Register VALUES(?,?,?,?,?,?,?)";
//        PreparedStatement stm = con.prepareStatement(query);
//        stm.setObject(1,r.getName());
//        stm.setObject(2,r.getAddress());
//        stm.setObject(3,r.getEmail());
//        stm.setObject(4,r.getUserType());
//        stm.setObject(5,r.getTelephoneNum());
//        stm.setObject(6,r.getUserName());
//        stm.setObject(7,r.getPassword());
//        return stm.executeUpdate()>0;
    }

    @Override
    public boolean add(Register register) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Register register) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Register search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Register> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
