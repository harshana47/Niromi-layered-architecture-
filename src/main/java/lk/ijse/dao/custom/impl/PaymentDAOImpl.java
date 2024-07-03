package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.Department;
import lk.ijse.entity.Payment;
import lk.ijse.model.PaymentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Payment (paymentId, method) VALUES (?, ?)",entity.getPaymentId(),entity.getMethod());
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Payment WHERE paymentId=?",id);
    }
    public Payment search(String paymentId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Payment WHERE paymentId=?",paymentId);
        if (resultSet.next()) {
            return new Payment(
                    resultSet.getString("paymentId"),
                    resultSet.getString("method")
            );
        }
        return null;
    }

    public String getPaymentIdByMethod(String paymentMethod) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute( "SELECT paymentId FROM payment WHERE method = ?",paymentMethod);
        if (resultSet.next()){
            return resultSet.getString("paymentId");
        }
        return null;
    }

    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Payment SET method=? WHERE paymentId=?",entity.getMethod(),entity.getPaymentId());
    }

    @Override
    public Payment findById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Payment findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List load() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> allPayment = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment");
        while (rst.next()) {
            Payment payment = new Payment(
                    rst.getString("paymentId"),
                    rst.getString("method")
            );
            allPayment.add(payment);
        }
        return allPayment;
    }

    public List<Payment> getAllPaymentMethods() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT paymentId, method FROM Payment");

                List<Payment> methods = new ArrayList<>();
                while (resultSet.next()) {
                    Payment payment = new Payment(
                        resultSet.getString("paymentId"),
                        resultSet.getString("method")
                    );
                    methods.add(payment);
                }
                return methods;
        }
    public List<Payment> getAllPaymentMethodNames() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT method FROM Payment");

        List<Payment> methods = new ArrayList<>();
        while (resultSet.next()) {
            Payment payment = new Payment(
                    resultSet.getString("method")
            );
            methods.add(payment);
        }
        return methods;
    }

}