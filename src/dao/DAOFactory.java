package dao;

import dao.custom.SavedOrderDAO;
import dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    //factory method
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case REGISTER:
                return new RegisterDAOImpl();
            case saveorder:
                return new SavedOrderDAOImpl();

            default:
                return null;
        }
    }

    public enum DAOTypes {
        CUSTOMER, ITEM, ORDER,REGISTER,saveorder
    }

}
