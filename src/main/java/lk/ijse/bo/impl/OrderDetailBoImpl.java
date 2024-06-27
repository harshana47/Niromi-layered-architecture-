package lk.ijse.bo.impl;

import javafx.scene.control.Alert;
import lk.ijse.bo.custom.OrderDetailBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDetailDAO;
import lk.ijse.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.db.DbConnection;
import lk.ijse.model.OrderProductDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailBoImpl implements OrderDetailBO {

    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    public boolean saveOrderProductDetail(List<OrderProductDetailDTO> odList) {
        return orderDetailDAO.saveOrderProductDetail(odList);
    }
    public List<OrderProductDetailDTO> getAllOrderProductDetails() throws SQLException, ClassNotFoundException {
        return orderDetailDAO.getAllOrderProductDetails();
    }
}