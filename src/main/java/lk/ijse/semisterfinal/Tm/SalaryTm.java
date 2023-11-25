package lk.ijse.semisterfinal.Tm;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class SalaryTm {
    private String employeeId;
    private String employeeName;
    private String date;
    private double salary;
    private Button removeBtn;


    public static ChoiceDialog<Object> getSelectionModel() {
        ChoiceDialog<Object> choiceDialog = new ChoiceDialog<>();
        choiceDialog.setTitle("Delete");
        choiceDialog.setHeaderText("Are you sure you want to delete this item?");
        return choiceDialog;
    }
}
