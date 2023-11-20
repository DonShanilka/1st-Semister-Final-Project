package lk.ijse.semisterfinal.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CashiyerDTO {
    private String billId;
    private String itemId;
    private String itemName;
    private int qty;
    private double itemPrice;
    private double discount;
    private double total;
    private LocalDate date;

}
