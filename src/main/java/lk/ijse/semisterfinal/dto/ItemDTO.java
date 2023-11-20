package lk.ijse.semisterfinal.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor


public class ItemDTO {
    private String ItemCode;
    private String itemDetails;
    private double ItemPrice;
    private String SupplierId;
    private String WarrantyPeriod;

    public ItemDTO(String text, String text1, Object value) {

    }
}
