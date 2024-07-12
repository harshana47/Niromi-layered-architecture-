package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.User;
import lk.ijse.model.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends CrudDAO<User> {

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException;

    public boolean checkCredential(String userId, String password) throws SQLException, IOException, ClassNotFoundException;
}
