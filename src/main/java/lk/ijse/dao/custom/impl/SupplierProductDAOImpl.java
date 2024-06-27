package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierProductDetailDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.SupplierProductDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupplierProductDAOImpl implements SupplierProductDetailDAO {

    public boolean save(SupplierProductDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplierProductDetails (productId, supplierId) VALUES (?, ?)",entity.getProductId(),entity.getSupplierId());
    }

    @Override
    public boolean update(SupplierProductDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(String productId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplierProductDetails WHERE productId=?",productId);
    }

    @Override
    public SupplierProductDetail findById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public SupplierProductDetail findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void load(ObservableList<SupplierProductDetail> entityList) throws SQLException, ClassNotFoundException {

    }

    @Override
    public SupplierProductDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}