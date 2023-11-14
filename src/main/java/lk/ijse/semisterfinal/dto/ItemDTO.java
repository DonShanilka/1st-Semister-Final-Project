package lk.ijse.semisterfinal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class ItemDTO {
    private String ItemCode;
    private double ItemPrice;
    private String SupplierId;
    private String WarrantyPeriod;
    private String itemDetails;

    public ItemDTO(String itemCode, double itemPrice, String supplierId, String warrantyPeriod, String itemDetails) {
        ItemCode = itemCode;
        ItemPrice = itemPrice;
        SupplierId = supplierId;
        WarrantyPeriod = warrantyPeriod;
        this.itemDetails = itemDetails;
    }


    @Override
    public String toString() {
        return "ItemDTO{" +
                "ItemCode='" + ItemCode + '\'' +
                ", ItemPrice=" + ItemPrice +
                ", SupplierId='" + SupplierId + '\'' +
                ", WarrantyPeriod='" + WarrantyPeriod + '\'' +
                ", itemDetails='" + itemDetails + '\'' +
                '}';
    }
}
