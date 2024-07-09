package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class User implements Serializable {
    private String userId;
    private String name;
    private String password;
    private String phone;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public User(String userId) {
        this.userId = userId;
    }
}
