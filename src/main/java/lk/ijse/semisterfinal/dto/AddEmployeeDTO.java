package lk.ijse.semisterfinal.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class AddEmployeeDTO {
    private String employeeId;
    private String EmployeeName;
    private String EmpAddress;
    private int EmployeePhone;
    private String empDate;
    private String EmployeeGender;
    private String empPosition;

    public AddEmployeeDTO(String id, String name, String address, String contact, String date, String gender, String job) {
    }
}
