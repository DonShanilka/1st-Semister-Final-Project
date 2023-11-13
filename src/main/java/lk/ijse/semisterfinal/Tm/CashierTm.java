package lk.ijse.semisterfinal.Tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class CashierTm {

    private String itemId;
    private String itemName;
    private int qty;
    private double unitPrice;
    private double Total;
    private Button btn;

}
