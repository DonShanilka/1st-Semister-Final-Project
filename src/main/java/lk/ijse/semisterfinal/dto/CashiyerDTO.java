package lk.ijse.semisterfinal.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CashiyerDTO {
    private String ItemPrice;
    private String ItemName;
    private String billDate;
    private String txtQty;
    private String btnAddToBill;
    private String comItemId;

}
