package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Order;
import lk.ijse.model.OrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {

    public boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;

    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public OrderDTO findById(String id) throws SQLException, ClassNotFoundException;

    public OrderDTO findByPhone(String phone) throws SQLException, ClassNotFoundException;

    public List<OrderDTO> load() throws SQLException, ClassNotFoundException;

    public OrderDTO search(String id) throws SQLException, ClassNotFoundException;

    public String getNextOrderId() throws SQLException, ClassNotFoundException;

    public String getLastOrderId() throws SQLException, ClassNotFoundException;
}
