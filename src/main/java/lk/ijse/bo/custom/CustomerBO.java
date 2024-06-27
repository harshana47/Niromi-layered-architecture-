package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerBO extends SuperBO {

    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public boolean delete(String customerId) throws SQLException, ClassNotFoundException;

    public CustomerDTO findById(String id) throws SQLException, ClassNotFoundException;

    public CustomerDTO findByPhone(String phone) throws SQLException, ClassNotFoundException;

    public void load(ObservableList<CustomerDTO> customerDTOList) throws SQLException, ClassNotFoundException;

    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException;
}
