package lk.ijse.semisterfinal.Tm;

import javafx.scene.control.ChoiceDialog;
import lombok.*;

@Getter
@Setter
@ToString

public class ItemTm {

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

    public ItemTm(String supId) {
        SupplierId = supId;
    }
}


