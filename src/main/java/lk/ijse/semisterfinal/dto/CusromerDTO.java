package lk.ijse.semisterfinal.dto;


import lombok.*;

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

    public CusromerDTO(Object value, String text, Object value1) {
    }
}
