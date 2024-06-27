package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.Order;
import lk.ijse.model.OrderDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDAOImpl implements OrderDAO {

    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        boolean saved = SQLUtil.execute("INSERT INTO orders (orderId, orderDate, totalAmount, cusId, paymentId, promoId, ExpireDiscountStatus) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)",entity.getOrderId(), entity.getOrderDate(), entity.getTotalAmount(), entity.getCustomerId(), entity.getPaymentId(), entity.getPromoId(),entity.getExpireDiscountStatus());
            if (saved) {
                return true;
            }
        return false;
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order findById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Order findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void load(ObservableList<Order> EntityList) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        String lastOrderId = getLastOrderId();
        int nextOrderNumber = Integer.parseInt(lastOrderId.substring(1)) + 1;
        return "O" + String.format("%04d", nextOrderNumber);
    }

    public String getLastOrderId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString("orderId");
        } else {
            // Handle the case when no result is returned, e.g., throw an exception or return a default value
            throw new SQLException("No order ID found");
        }
    }

}