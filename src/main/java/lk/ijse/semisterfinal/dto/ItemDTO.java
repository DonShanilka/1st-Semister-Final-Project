package lk.ijse.semisterfinal.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString

public class ItemDTO {
    private String ItemCode;
    private String SupplierId;
    private double ItemPrice;
    private String WarrantyPeriod;
    private String itemDetails;

    public ItemDTO(String code, String details, double itemPrice, String supplierid, String warranty) {
    }

    public ItemDTO(String code, String details, String itemPrice, String supplierid, String warranty) {
    }
}
