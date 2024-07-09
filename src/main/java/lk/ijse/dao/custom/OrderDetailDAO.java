package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.OrderProductDetail;
import lk.ijse.model.OrderProductDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderProductDetail> {
    public boolean saveOrderProductDetail(List<OrderProductDetail> odList) throws ClassNotFoundException;
    public List<OrderProductDetail> getAllOrderProductDetails() throws SQLException, ClassNotFoundException;
}
