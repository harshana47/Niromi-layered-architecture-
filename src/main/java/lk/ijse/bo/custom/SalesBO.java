package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.model.OrderProductDetailDTO;

import java.sql.SQLException;

public interface SalesBO extends SuperBO {
    public OrderProductDetailDTO search(String orderId) throws SQLException, ClassNotFoundException;
}
