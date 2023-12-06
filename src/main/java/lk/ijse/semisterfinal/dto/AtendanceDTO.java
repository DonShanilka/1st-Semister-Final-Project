package lk.ijse.semisterfinal.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString

public class AtendanceDTO {
    private String date;
    private String employeeId;
    private String employeeName;

    public AtendanceDTO(String date, String id, String name) {
        this.date = date;
        employeeId = id;
        employeeName = name;
    }
}
