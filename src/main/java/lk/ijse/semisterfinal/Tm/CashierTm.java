package lk.ijse.semisterfinal.Tm;

import javafx.scene.control.ChoiceDialog;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CashierTm {
    private String ItemPrice;
    private String ItemName;
    private String billDate;
    private String txtQty;
    private String btnAddToBill;
    private String comItemId;


    public CashierTm(String code, String description, int qty) {
        comItemId = code;
        ItemName = description;
        txtQty = String.valueOf(qty);
    }
}
