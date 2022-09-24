package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import model.Order;
import model.Register;

import java.sql.SQLException;

public interface RegisterDAO extends CrudDAO<Register, String> {
    boolean saveRegister(Register r) throws SQLException, ClassNotFoundException;
}
