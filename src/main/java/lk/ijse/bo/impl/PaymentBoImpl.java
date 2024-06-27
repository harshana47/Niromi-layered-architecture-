package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.model.PaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    public boolean save(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(paymentDTO);
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }
    public PaymentDTO search(String paymentId) throws SQLException, ClassNotFoundException {
        return paymentDAO.search(paymentId);
    }

    public String getPaymentIdByMethod(String paymentMethod) throws SQLException, ClassNotFoundException {
        return paymentDAO.getPaymentIdByMethod(paymentMethod);
    }

    public boolean update(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(paymentDTO);
    }

    @Override
    public PaymentDTO findById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public PaymentDTO findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void load(ObservableList<PaymentDTO> DTOList) throws SQLException, ClassNotFoundException {
       paymentDAO.load(DTOList);
    }

    public List<PaymentDTO> getAllPaymentMethods() throws SQLException, ClassNotFoundException {
            return paymentDAO.getAllPaymentMethods();
        }

    public List<PaymentDTO> getAllPaymentMethodNames() throws SQLException, ClassNotFoundException {
        return paymentDAO.getAllPaymentMethodNames();
    }
}