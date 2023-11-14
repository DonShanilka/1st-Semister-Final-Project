package lk.ijse.semisterfinal.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class SupplierDTO {
    private String SupId;
    private String SupName;
    private String supItemName;
    private String supqty;
    private LocalDate supdate;
    private String supMobile;

}
