package lk.ijse.semisterfinal.dto;

import lk.ijse.semisterfinal.Tm.CashierTm;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CashiyerDTO {
    private String orderId;
    private LocalDate date;
    private String customerId;
    private List<CashierTm> cartTmList = new ArrayList<>();

}
