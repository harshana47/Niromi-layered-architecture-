package lk.ijse.entity;

import lk.ijse.model.OrderProductDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private String orderId;
    private LocalDate orderDate;
    private Double totalAmount;
    private String customerId;
    private String paymentId;
    private String promoId;
    private String expireDiscountStatus;
    private List<OrderProductDetail> orderProductDetailList;


    public Order(String orderId, LocalDate orderDate, Double totalAmount, String customerId, String paymentId, String promoId, String expireDiscountStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.customerId = customerId;
        this.paymentId = paymentId;
        this.promoId = promoId;
        this.expireDiscountStatus = expireDiscountStatus;
    }
}
