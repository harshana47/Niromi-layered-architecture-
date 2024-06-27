package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class UserDTO implements Serializable {
    private String userId;
    private String name;
    private String password;
    private String phone;

    public UserDTO(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
