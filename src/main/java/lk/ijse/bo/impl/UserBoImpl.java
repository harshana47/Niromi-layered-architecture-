package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dao.custom.impl.UserDAOImpl;
import lk.ijse.entity.User;
import lk.ijse.model.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    public List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        List<User> users = userDAO.getAllUsers();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User u:users){
            UserDTO userDTO = new UserDTO(u.getUserId(),u.getName(),u.getPassword(),u.getPhone());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public UserDTO findById(String userId) throws SQLException, ClassNotFoundException {
        try{
            User user = userDAO.findById(userId);
            return new UserDTO(user.getUserId(),user.getName(),user.getPassword(),user.getPhone());
        }catch (SQLException e) {
            throw new ClassNotFoundException("User not found");
        }
    }

    @Override
    public boolean save(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(dto.getUserId(),dto.getName(),dto.getPassword(),dto.getPhone()));
    }

    @Override
    public boolean update(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(dto.getUserId(),dto.getName(),dto.getPassword(),dto.getPhone()));
    }

    @Override
    public boolean delete(String userId) throws SQLException, ClassNotFoundException {
        return userDAO.delete(userId);
    }

    @Override
    public UserDTO findByPhone(String phone) throws SQLException, ClassNotFoundException {
       try {
           User user = userDAO.findByPhone(phone);
           return new UserDTO(user.getUserId(),user.getName(),user.getPassword(),user.getPhone());
       }catch (SQLException e) {
           throw new ClassNotFoundException("User not found");
       }
    }

    @Override
    public void load(ObservableList<UserDTO> DTOList) throws SQLException, ClassNotFoundException {

    }

    @Override
    public UserDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean checkCredential(String userId, String password) throws SQLException, IOException, ClassNotFoundException {
        return userDAO.checkCredential(userId, password);
    }
}
