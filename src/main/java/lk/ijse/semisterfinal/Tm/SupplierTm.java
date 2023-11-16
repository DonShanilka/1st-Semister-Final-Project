package lk.ijse.semisterfinal.Tm;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class SupplierTm {
    private String SupId;
    private String SupName;
    private String supItemName;
    private int supqty;
    private String supMobile;

}
