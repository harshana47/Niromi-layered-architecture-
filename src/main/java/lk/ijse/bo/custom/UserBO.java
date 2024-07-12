package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.model.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {

    public List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException;

    public UserDTO findById(String userId) throws SQLException, ClassNotFoundException;

    public boolean save(UserDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(UserDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String userId) throws SQLException, ClassNotFoundException;

    public UserDTO findByPhone(String phone) throws SQLException, ClassNotFoundException;

    public void load(ObservableList<UserDTO> DTOList) throws SQLException, ClassNotFoundException;

    public UserDTO search(String id) throws SQLException, ClassNotFoundException;

    public boolean checkCredential(String userId, String password) throws SQLException, IOException, ClassNotFoundException;
}
