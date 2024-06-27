package lk.ijse.entity;

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
}
