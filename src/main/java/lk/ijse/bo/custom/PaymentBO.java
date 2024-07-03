package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.model.PaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {

    public boolean save(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public PaymentDTO search(String paymentId) throws SQLException, ClassNotFoundException;

    public String getPaymentIdByMethod(String paymentMethod) throws SQLException, ClassNotFoundException;


    public boolean update(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;

    public PaymentDTO findById(String id) throws SQLException, ClassNotFoundException;

    public PaymentDTO findByPhone(String phone) throws SQLException, ClassNotFoundException;

    public List<PaymentDTO> load() throws SQLException, ClassNotFoundException;

    public List<PaymentDTO> getAllPaymentMethods() throws SQLException, ClassNotFoundException;

    public List<PaymentDTO> getAllPaymentMethodNames() throws SQLException, ClassNotFoundException;
}
