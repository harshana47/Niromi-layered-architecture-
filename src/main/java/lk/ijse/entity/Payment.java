package lk.ijse.entity;

import java.io.Serializable;

public class Payment implements Serializable{
    private String paymentId;
    private String method;

    public Payment(String paymentId, String method) {
        this.paymentId = paymentId;
        this.method = method;
    }

    public Payment(String method) {
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
