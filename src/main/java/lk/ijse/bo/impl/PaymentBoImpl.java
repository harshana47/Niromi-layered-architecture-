package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Payment;
import lk.ijse.model.CustomerDTO;
import lk.ijse.model.PaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    public boolean save(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(paymentDTO.getPaymentId(),paymentDTO.getMethod()));
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }
    public PaymentDTO search(String paymentId) throws SQLException, ClassNotFoundException {
        try{
            Payment payment = paymentDAO.search(paymentId);
            return new PaymentDTO(payment.getPaymentId(), payment.getMethod());
        }catch (SQLException e){
            throw new SQLException("Payment not found");
        }catch (ClassNotFoundException e){
            throw new ClassNotFoundException("Class not found");
        }
    }

    public String getPaymentIdByMethod(String paymentMethod) throws SQLException, ClassNotFoundException {
        return paymentDAO.getPaymentIdByMethod(paymentMethod);
    }

    public boolean update(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(paymentDTO.getPaymentId(), paymentDTO.getMethod()));
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
    public List<PaymentDTO> load() throws SQLException, ClassNotFoundException {
       List<Payment> payments = paymentDAO.load();
       ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
       for(Payment p:payments){
           PaymentDTO paymentDTO = new PaymentDTO(p.getPaymentId(),p.getMethod());
           paymentDTOS.add(paymentDTO);
       }
       return paymentDTOS;
    }

    public List<PaymentDTO> getAllPaymentMethods() throws SQLException, ClassNotFoundException {
            List<Payment> payments = paymentDAO.getAllPaymentMethods();
            ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        for(Payment p:payments){
            PaymentDTO paymentDTO = new PaymentDTO(p.getPaymentId(),p.getMethod());
            paymentDTOS.add(paymentDTO);
        }
        return paymentDTOS;
    }

    public List<PaymentDTO> getAllPaymentMethodNames() throws SQLException, ClassNotFoundException {
        List<Payment> payments = paymentDAO.getAllPaymentMethodNames();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        for(Payment p:payments){
            PaymentDTO paymentDTO = new PaymentDTO(p.getPaymentId(),p.getMethod());
            paymentDTOS.add(paymentDTO);
        }
        return paymentDTOS;
    }
}