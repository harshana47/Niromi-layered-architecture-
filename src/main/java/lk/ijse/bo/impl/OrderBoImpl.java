package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.OrderBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dao.custom.impl.OrderDAOImpl;
import lk.ijse.entity.Order;
import lk.ijse.model.OrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderBoImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    public boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
       return orderDAO.save(new Order(orderDTO.getOrderId(), orderDTO.getOrderDate(), orderDTO.getTotalAmount(), orderDTO.getCustomerId(), orderDTO.getPaymentId(), orderDTO.getPromoId(), orderDTO.getExpireDiscountStatus()));
    }

    @Override
    public boolean update(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return orderDAO.update(new Order(orderDTO.getOrderId(), orderDTO.getOrderDate(), orderDTO.getTotalAmount(), orderDTO.getCustomerId(), orderDTO.getPaymentId(), orderDTO.getPromoId(), orderDTO.getExpireDiscountStatus()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

    @Override
    public OrderDTO findById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDTO findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<OrderDTO> load() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.getNextOrderId();
    }

    public String getLastOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.getLastOrderId();
    }

}
