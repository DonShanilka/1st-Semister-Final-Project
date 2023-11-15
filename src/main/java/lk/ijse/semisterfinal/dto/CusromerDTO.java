package lk.ijse.semisterfinal.dto;


import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class CusromerDTO {
    private String txtCustId;
    private String txtCustName;
    private String txtCustAddress;
    private String txtCustMobile;
    private String txtCustPayment;
    private String txtCustitemId;

}
