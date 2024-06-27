// Department.java (model class)
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
public class DepartmentDTO implements Serializable {
    private String depId;
    private String name;
    private int staffCount;
}
