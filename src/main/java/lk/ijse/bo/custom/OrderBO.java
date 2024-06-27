package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.model.OrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderBO extends SuperBO {

    public boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;

    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public OrderDTO findById(String id) throws SQLException, ClassNotFoundException;

    public OrderDTO findByPhone(String phone) throws SQLException, ClassNotFoundException;

    public void load(ObservableList<OrderDTO> DTOList) throws SQLException, ClassNotFoundException;

    public OrderDTO search(String id) throws SQLException, ClassNotFoundException;

    public String getNextOrderId() throws SQLException, ClassNotFoundException;

    public String getLastOrderId() throws SQLException, ClassNotFoundException;
}
