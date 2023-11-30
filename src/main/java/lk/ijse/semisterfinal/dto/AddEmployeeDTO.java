package lk.ijse.semisterfinal.dto;

import lombok.*;


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
    private String empPosition;

    public AddEmployeeDTO(String eid, String name, String address, String mobile, String position) {
        this.employeeId = eid;
        this.EmployeeName = name;
        this.EmpAddress = address;
        this.EmployeePhone = Integer.parseInt(mobile);
        this.empPosition = position;
    }

    public AddEmployeeDTO(String id, String name, String address, int contact, String date, String job) {
        this.employeeId = id;
        this.EmployeeName = name;
        this.EmpAddress = address;
        this.EmployeePhone = contact;
        this.empDate = date;
        this.empPosition = job;
    }

}
