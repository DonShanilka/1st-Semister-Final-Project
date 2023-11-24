package lk.ijse.semisterfinal.Tm;

import javafx.scene.control.Button;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class AtendanceTm {
    private String employeeId;
    private String employeeName;
    private String date;
    private Button removeBtn;
    private boolean Present;

    public AtendanceTm(String employeeId, String employeeName, Button removeBtn) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.removeBtn = removeBtn;
    }

}
