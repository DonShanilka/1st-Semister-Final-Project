package lk.ijse.semisterfinal.Tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class EmployeeTm {
    private String id;
    private String name;
   // private String gender;
    private String mobile;
    private String address;
    private String date;
    private String possition;
    private String salary;


    public EmployeeTm(String employeeId, String employeeName, String empAddress, int employeePhone, String empDate, String empPosition) {
        this.id = employeeId;
        this.name = employeeName;
        this.address = empAddress;
        this.mobile = String.valueOf(employeePhone);
        this.date = empDate;
        //this.gender = employeeGender;
        this.possition = empPosition;
    }
}
