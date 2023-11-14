package lk.ijse.semisterfinal.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ItemDTO {
    private String ItemCode;
    private double ItemPrice;
    private String SupplierId;
    private String WarrantyPeriod;
    private String itemDetails;

    public ItemDTO(String code, String details, String itemPrice, String supplierid, String warranty) {
    }
}
