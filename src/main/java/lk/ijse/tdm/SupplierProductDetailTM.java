package lk.ijse.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierProductDetailTM {
    private String supplierProductId;
    private String productId;
    private String supplierId;

}
