package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Order;
import lk.ijse.model.OrderDTO;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
    public String getNextOrderId() throws SQLException, ClassNotFoundException;

    public String getLastOrderId() throws SQLException, ClassNotFoundException;
}
