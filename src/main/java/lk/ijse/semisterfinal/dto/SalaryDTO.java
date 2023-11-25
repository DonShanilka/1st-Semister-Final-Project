package lk.ijse.semisterfinal.dto;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SalaryDTO {

    private Date date;
    private String employeeId;
    private String employeeName;
    private double salary;


    public SalaryDTO(double amount, String id, String name, LocalDate date1) {
        this.date = Date.valueOf(date1);
        this.employeeId = id;
        this.employeeName = name;
        this.salary = amount;
    }
}
