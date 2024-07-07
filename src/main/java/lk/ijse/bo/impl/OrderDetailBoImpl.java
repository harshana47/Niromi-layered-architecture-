package lk.ijse.bo.impl;

import javafx.scene.control.Alert;
import lk.ijse.bo.custom.OrderDetailBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDetailDAO;
import lk.ijse.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.Department;
import lk.ijse.entity.OrderProductDetail;
import lk.ijse.model.DepartmentDTO;
import lk.ijse.model.OrderProductDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailBoImpl implements OrderDetailBO {

    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    public boolean saveOrderProductDetail(List<OrderProductDetail> odList) {
        return orderDetailDAO.saveOrderProductDetail(odList);
    }
    public List<OrderProductDetailDTO> getAllOrderProductDetails() throws SQLException, ClassNotFoundException {
        try {
            List<OrderProductDetail> orderProductDetails = orderDetailDAO.getAllOrderProductDetails();
            List<OrderProductDetailDTO> orderProductDetailDTOS = new ArrayList<>();
            for (OrderProductDetail od : orderProductDetails) {
                orderProductDetailDTOS.add(new OrderProductDetailDTO(od.getOrderId(), od.getProductId(), od.getQty(), od.getTotal(), od.getOrderDate()));
            }
            return orderProductDetailDTOS;
        }catch (SQLException e){
            throw new SQLException("Error getting order detail"+ e.getMessage());
        }
    }
}