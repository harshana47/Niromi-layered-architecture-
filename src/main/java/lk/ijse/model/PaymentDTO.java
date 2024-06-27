package lk.ijse.model;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

public class PaymentDTO implements Serializable{
    private String paymentId;
    private String method;

    public PaymentDTO(String paymentId, String method) {
        this.paymentId = paymentId;
        this.method = method;
    }

    public PaymentDTO(String method) {
        this.method = method;
    }

    // Getters and setters
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    @Override
    public String toString() {
        return method; // Assuming 'method' is the payment method field in PaymentDTO
    }
}
