package lk.ijse.bo.impl;

import lk.ijse.bo.custom.SalesBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SalesDAO;
import lk.ijse.dao.custom.impl.SalesDAOImpl;
import lk.ijse.entity.Order;
import lk.ijse.entity.OrderProductDetail;
import lk.ijse.model.OrderProductDetailDTO;

import java.sql.SQLException;

public class SalesBoImpl implements SalesBO {

    SalesDAO salesDAO = (SalesDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SALES);

    public OrderProductDetailDTO search(String orderId) throws SQLException, ClassNotFoundException {
        try {
            OrderProductDetail orderProductDetail = salesDAO.search(orderId);
            return new OrderProductDetailDTO(orderProductDetail.getOrderId(), orderProductDetail.getProductId(), orderProductDetail.getQty(), orderProductDetail.getTotal(), orderProductDetail.getOrderDate());
        }catch (SQLException e) {
            throw new SQLException("Error searching");
        }
    }
}
