package lk.ijse.bo.custom;

import javafx.scene.control.Alert;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.model.OrderProductDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderDetailBO extends SuperBO {

    public boolean saveOrderProductDetail(List<OrderProductDetailDTO> odList);

    public List<OrderProductDetailDTO> getAllOrderProductDetails() throws SQLException, ClassNotFoundException;
}
