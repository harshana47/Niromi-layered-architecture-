package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SalesDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.OrderProductDetail;
import lk.ijse.model.OrderProductDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void load(ObservableList DTOList) throws SQLException, ClassNotFoundException {

    }

    public OrderProductDetail search(String orderId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "SELECT * FROM orderProductDetails WHERE orderId=?",orderId);
    }
}
