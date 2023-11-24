package lk.ijse.semisterfinal.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class AtendanceDTO {
    private String date;
    private String employeeId;
    private String employeeName;
    private boolean Present;
}
