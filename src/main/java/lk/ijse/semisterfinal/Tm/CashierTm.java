package lk.ijse.semisterfinal.Tm;

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

}
