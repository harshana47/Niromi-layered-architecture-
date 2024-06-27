package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data

public class EmployeeDTO implements Serializable {
    private String employeeId;
    private String name;
    private String depId;
    private String position;
    private String duty;
    private String email;

}
