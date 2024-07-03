package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SalesDAO;
import lk.ijse.entity.OrderProductDetail;

import java.sql.SQLException;
import java.util.List;

public class SalesDAOImpl implements SalesDAO {
    @Override
    public boolean save(Object dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Object dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Object findById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Object findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List load() throws SQLException, ClassNotFoundException {

        return null;
    }

    public OrderProductDetail search(String orderId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "SELECT * FROM orderProductDetails WHERE orderId=?",orderId);
    }
}
