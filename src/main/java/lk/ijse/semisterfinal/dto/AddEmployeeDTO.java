package lk.ijse.semisterfinal.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class AddEmployeeDTO {
    private String employeeId;
    private String EmployeeName;
    private String EmployeeGender;
    private int EmployeePhone;
    private String EmpAddress;
    private LocalDate empDate;
    private String empPosition;

    public AddEmployeeDTO(String id, String name, String address, int tele, LocalDate date, String gender, String position) {
    }
}
