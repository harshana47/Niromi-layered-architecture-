package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.model.DepartmentDTO;
import lk.ijse.entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier (supplierId, name, address, contact, email) VALUES (?, ?, ?, ?, ?)",
                entity.getSupplierId(), entity.getName(), entity.getAddress(), entity.getContact(), entity.getEmail());
    }

    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier WHERE supplierId=?", supplierId);
    }

    @Override
    public Supplier findById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Supplier findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List load() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET name=?, address=?, contact=?, email=? WHERE supplierId=?",
                entity.getName(), entity.getAddress(), entity.getContact(), entity.getEmail(), entity.getSupplierId());
    }

    public List<Supplier> getAllSuppliers() throws SQLException, ClassNotFoundException {
        List<Supplier> suppliers = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier");

            while (resultSet.next()) {
                Supplier supplier = new Supplier(
                        resultSet.getString("supplierId"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("contact"),
                        resultSet.getString("email")
                );
                suppliers.add(supplier);
            }
        return suppliers;
    }

    public Supplier search(String supplierId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier WHERE supplierId=?", supplierId);
        if (resultSet.next()) {
            return new Supplier(
                    resultSet.getString("supplierId"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("contact"),
                    resultSet.getString("email")
            );
        }
        return null;
    }

    public List<String> getAllSupplierNames() throws SQLException, ClassNotFoundException {
        List<String> supplierNames = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute( "SELECT name FROM supplier");

        while (resultSet.next()) {
            supplierNames.add(resultSet.getString("name"));
        }

        return supplierNames;
    }

    public String getSupplierIdByName(String supplierName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT supplierId FROM supplier WHERE name=?", supplierName);
        if (resultSet.next()) {
            return resultSet.getString("supplierId");
        }
        return null;
    }
}
