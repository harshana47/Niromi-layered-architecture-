package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerBoImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(customerDTO);
    }

    @Override
    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(customerDTO);
    }

    @Override
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(customerId);
    }

    @Override
    public CustomerDTO findById(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.findById(id);
    }

    @Override
    public CustomerDTO findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return customerDAO.findByPhone(phone);
    }

    @Override
    public void load(ObservableList<CustomerDTO> customerDTOList) throws SQLException, ClassNotFoundException {
        customerDAO.load(customerDTOList);
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }
}
