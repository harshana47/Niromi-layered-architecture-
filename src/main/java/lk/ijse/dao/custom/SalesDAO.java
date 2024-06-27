package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.OrderProductDetail;
import lk.ijse.model.OrderProductDetailDTO;

import java.sql.SQLException;

public interface SalesDAO extends CrudDAO{

    public OrderProductDetail search(String orderId) throws SQLException, ClassNotFoundException;

}
