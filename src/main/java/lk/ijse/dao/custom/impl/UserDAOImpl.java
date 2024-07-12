package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.User;
import lk.ijse.entity.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT * FROM user");
    }

    @Override
    public User findById(String userId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT * FROM User WHERE userId = ?",userId);
    }

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO User VALUES (?, ?, ?, ?)", entity.getUserId(), entity.getName(), entity.getPassword(), entity.getPhone());
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE User SET password = ? WHERE userId = ?",entity.getPassword(),entity.getUserId());
    }

    @Override
    public boolean delete(String userId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM User WHERE userId = ?",userId);
    }

    @Override
    public User findByPhone(String phone) throws SQLException, ClassNotFoundException {
       ResultSet resultSet = SQLUtil.execute("SELECT * FROM User WHERE phone =?",phone);
        if (resultSet.next()){
            return new User(
                    resultSet.getString("userId")
            );
        }
        return null;
    }

    @Override
    public List load() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean checkCredential(String userId, String password) throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT password FROM user WHERE userId = ?",userId);
        if (resultSet.next()) {
            String dbPw = resultSet.getString("password");

            if (password.equals(dbPw)) {
                return true;
                //navigateToTheDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR, "Sorry! Password is incorrect!").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Sorry! User ID not found!").show();
        }
        return false;
    }
}
