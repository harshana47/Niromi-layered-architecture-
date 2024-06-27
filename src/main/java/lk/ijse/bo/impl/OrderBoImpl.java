package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.OrderBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dao.custom.impl.OrderDAOImpl;
import lk.ijse.model.OrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderBoImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    public boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
       return orderDAO.save(orderDTO);
    }

    @Override
    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return orderDAO.update(dto);
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
    public void load(ObservableList<OrderDTO> DTOList) throws SQLException, ClassNotFoundException {

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
