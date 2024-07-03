package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.entity.Customer;
import lk.ijse.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDTO.getCustomerId(), customerDTO.getName(), customerDTO.getEmail(), customerDTO.getPhone()));
    }

    @Override
    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCustomerId(), customerDTO.getName(), customerDTO.getEmail(), customerDTO.getPhone()));
    }

    @Override
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(customerId);
    }

    @Override
    public CustomerDTO findById(String id) throws SQLException, ClassNotFoundException {
        try{
            Customer customer = customerDAO.findById(id);
            return new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getEmail(), customer.getPhone());
        }catch (SQLException e){
            throw new SQLException("Error in finding customer by id: " + e.getMessage());
        }catch (ClassNotFoundException e){
            throw new ClassNotFoundException("Error in finding customer by id: " + e.getMessage());
        }
    }

    @Override
    public CustomerDTO findByPhone(String phone) throws SQLException, ClassNotFoundException {
        try{
            Customer customer = customerDAO.findByPhone(phone);
            return new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getEmail(), customer.getPhone());
        }catch (SQLException e){
            throw new SQLException("Error in finding customer by id: " + e.getMessage());
        }catch (ClassNotFoundException e){
            throw new ClassNotFoundException("Error in finding customer by id: " + e.getMessage());
        }
    }

    @Override
    public List<CustomerDTO> load() throws SQLException, ClassNotFoundException {
        List<Customer> customers = customerDAO.load();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer c:customers){
            CustomerDTO customerDTO = new CustomerDTO(c.getCustomerId(), c.getName(), c.getEmail(),c.getPhone());
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        try{
            Customer customer = customerDAO.search(id);
            return new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getEmail(), customer.getPhone());
        }catch (SQLException e){
            throw new SQLException("Error in finding customer : " + e.getMessage());
        }catch (ClassNotFoundException e){
            throw new ClassNotFoundException("Error in finding customer: " + e.getMessage());
        }
    }
}
