package lk.ijse.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
public class SupplierDTO implements Serializable {
    private String supplierId;
    private String name;
    private String address;
    private String contact;
    private String email;
    private List<SupplierProductDetailDTO> supplierProductDetailDTOList;

    public SupplierDTO(String supplierId, String name, String address, String contact, String email, List<SupplierProductDetailDTO> supplierProductDetailDTOList) {
        this.supplierId = supplierId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.supplierProductDetailDTOList = supplierProductDetailDTOList;
    }

    public SupplierDTO(String supplierId, String name, String address, String contact, String email) {
        this.supplierId = supplierId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.supplierProductDetailDTOList = new ArrayList<>(); // Initialize the list
    }
}
