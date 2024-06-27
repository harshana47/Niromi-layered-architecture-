package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Payment;
import lk.ijse.model.PaymentDTO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {

    public String getPaymentIdByMethod(String paymentMethod) throws SQLException, ClassNotFoundException;

    public List<Payment> getAllPaymentMethods() throws SQLException, ClassNotFoundException;

    public List<Payment> getAllPaymentMethodNames() throws SQLException, ClassNotFoundException;
}
