package lk.ijse.semisterfinal.controller;

import com.sun.javafx.menu.MenuItemBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.semisterfinal.Tm.AtendanceTm;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;
import lk.ijse.semisterfinal.dto.AtendanceDTO;
import lk.ijse.semisterfinal.dto.ItemDTO;
import lk.ijse.semisterfinal.model.AddEmployeeModel;
import lk.ijse.semisterfinal.model.AtendanceModel;
import lk.ijse.semisterfinal.model.ItemModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class AttendanceController {

    public AnchorPane root;

    public DatePicker date;
    public ComboBox comEmpId;
    public TextField lblName;
    public TableView atendanceTm;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colDate;
    public TableColumn colAction;


    private AddEmployeeModel employeeModel = new AddEmployeeModel();

    private AtendanceModel attendanceModel = new AtendanceModel();

    private ObservableList<AtendanceTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        date.setPromptText(String.valueOf(LocalDate.now()));
        loadAllEmployee();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));
    }

    public void btnMarkAttendanceOnAction(ActionEvent actionEvent) {
        String empId = (String) comEmpId.getValue();
        String empName = lblName.getText();
        Button btn = new Button("Remove");

        btn.setCursor(Cursor.HAND);

        if (!obList.isEmpty()) {
            for (int i = 0; i < atendanceTm.getItems().size(); i++) {
                if (colId.getCellData(i).equals(empId)) {

                    atendanceTm.refresh();
                    return;
                }
            }
        }

        var incomeTm = new AtendanceTm(empId, empName, btn);

        obList.add(incomeTm);

        atendanceTm.setItems(obList);
    }

    /*private void setRemoveBtnAction(Button RemoveBtn, String teacherId) {
        MenuItemBase Removebtn = null;
        Removebtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
            }
        });
        RemoveBtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional <ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                //Remove the selected teacher from the attendance list
                obList.removeIf(attendanceTm -> attendanceTm.getEmployeeId().equals(teacherId));
                atendanceTm.setItems(obList);
            }
        });
    }*/


    public void cmbIdOnAction(ActionEvent actionEvent) {
        String id = (String) comEmpId.getValue();

        AddEmployeeDTO dto = employeeModel.search(id);  ////////////////////////////////////////////////////////
        lblName.setText(dto.getEmployeeName());
    }

    private void loadAllEmployee() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<AddEmployeeDTO> teacherDtos = employeeModel.getAllEmployee();

            for (AddEmployeeDTO dto : teacherDtos) {
                obList.add(dto.getEmployeeId());
            }
            comEmpId.setItems(obList);
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        }
    }

    public void markAtendanseOnAction(ActionEvent event) {
        String date = colDate.getText();
        String id = String.valueOf(comEmpId.getValue());
        String name = lblName.getText();

        var dto = new AtendanceDTO(date,id,name);

        try {
            boolean isaddite = AtendanceModel.addAttendance(dto);
            if (isaddite) {
                new Alert(Alert.AlertType.CONFIRMATION, "Add Successful").show();
                loadAllEmployee();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    public void cmbEmpIdOnAction(ActionEvent event) {
        String id = (String) comEmpId.getValue();
        try {
            AddEmployeeDTO dto = AddEmployeeModel.searchEmployee(id);
            lblName.setText(dto.getEmployeeName());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void BackOnAction(ActionEvent event) {

    }

}
