package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO implements Serializable {

    private String customerId;
    private String name;
    private String email;
    private String phone;
}
