package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDetailDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.OrderProductDetail;
import lk.ijse.model.OrderProductDetailDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    public boolean saveOrderProductDetail(List<OrderProductDetail> odList) {
        System.out.println("Saving order product detail information");
        Connection connection = null;
        PreparedStatement pst = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            String sql = "INSERT INTO orderProductDetails (orderId, productId, quantity, itemPrice, date) VALUES (?,?,?,?,?)";
            pst = connection.prepareStatement(sql);
            //aa

            for (OrderProductDetail od : odList) {
                System.out.println(od);
                pst.setString(1, od.getOrderId());
                pst.setString(2, od.getProductId());
                pst.setInt(3, od.getQty());
                pst.setDouble(4, od.getTotal());
                pst.setObject(5, od.getOrderDate());

                boolean isSaved = pst.executeUpdate() > 0;
                if (!isSaved) {
                    return false;
                }
            }
            System.out.println("OrderProductDetails saved");
            return true;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.CONFIRMATION, e.getMessage()).show();

            return false;
        }
    }
    public List<OrderProductDetail> getAllOrderProductDetails() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM orderProductDetails");
        List<OrderProductDetail> orderProductDetails = new ArrayList<>();
        while (resultSet.next()) {
            orderProductDetails.add(new OrderProductDetail(
                    resultSet.getString("orderId"),
                    resultSet.getString("productId"),
                    resultSet.getInt("quantity"),
                    resultSet.getDouble("itemPrice"),
                    resultSet.getDate("date").toLocalDate()
            ));
        }
        return orderProductDetails;
    }

    @Override
    public boolean save(OrderProductDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderProductDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderProductDetail findById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderProductDetail findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List load() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderProductDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}