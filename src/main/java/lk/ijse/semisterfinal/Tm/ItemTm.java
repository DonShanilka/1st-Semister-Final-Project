package lk.ijse.semisterfinal.Tm;

import lombok.*;

public class ItemTm {
    @Getter
    @Setter

        private String ItemCode;
        private String itemDetails;
        private double ItemPrice;
        private String SupplierId;
        private String WarrantyPeriod;

    public ItemTm(String itemCode, String itemDetails, double itemPrice, String supplierId, String warrantyPeriod) {
        ItemCode = itemCode;
        this.itemDetails = itemDetails;
        ItemPrice = itemPrice;
        SupplierId = supplierId;
        WarrantyPeriod = warrantyPeriod;
    }


    @Override
    public String toString() {
        return "ItemTm{" +
                "ItemCode='" + ItemCode + '\'' +
                ", itemDetails='" + itemDetails + '\'' +
                ", ItemPrice=" + ItemPrice +
                ", SupplierId='" + SupplierId + '\'' +
                ", WarrantyPeriod='" + WarrantyPeriod + '\'' +
                '}';
    }
}


