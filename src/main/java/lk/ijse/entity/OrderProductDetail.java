package lk.ijse.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDetail implements Serializable {
    private String orderId;
    private String productId;
    private int qty;
    private double total;
    private LocalDate orderDate; // New field for order date

}
