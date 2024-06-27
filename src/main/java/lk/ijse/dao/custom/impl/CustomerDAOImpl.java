package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.entity.Customer;
import lk.ijse.model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer (cusId, name, email, phone) VALUES (?, ?, ?, ?)",
                entity.getCustomerId(), entity.getName(), entity.getEmail(), entity.getPhone());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET name=?, email=?, phone=? WHERE cusId=?",
                entity.getName(), entity.getEmail(), entity.getPhone(), entity.getCustomerId());
    }

    @Override
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE cusId=?", customerId);
    }

    @Override
    public Customer findById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE cusId=?", id);
        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString("cusId"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone")
            );
        }
        return null;
    }

    @Override
    public Customer findByPhone(String phone) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE phone=?", phone);
        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString("cusId"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone")
            );
        }
        return null;
    }

    @Override
    public void load(ObservableList<Customer> customerList) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");
        while (resultSet.next()) {
            customerList.add(new Customer(
                    resultSet.getString("cusId"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone")
            ));
        }
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
