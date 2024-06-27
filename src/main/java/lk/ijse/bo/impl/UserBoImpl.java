package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dao.custom.impl.UserDAOImpl;
import lk.ijse.model.UserDTO;

import java.sql.SQLException;
import java.util.List;

public class UserBoImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    public List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        return userDAO.getAllUsers();
    }

    @Override
    public UserDTO findById(String userId) throws SQLException, ClassNotFoundException {
        return userDAO.findById(userId);
    }

    @Override
    public boolean save(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(dto);
    }

    @Override
    public boolean update(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.update(dto);
    }

    @Override
    public boolean delete(String userId) throws SQLException, ClassNotFoundException {
        return userDAO.delete(userId);
    }

    @Override
    public UserDTO findByPhone(String phone) throws SQLException, ClassNotFoundException {
       return userDAO.findByPhone(phone);
    }

    @Override
    public void load(ObservableList<UserDTO> DTOList) throws SQLException, ClassNotFoundException {

    }

    @Override
    public UserDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
