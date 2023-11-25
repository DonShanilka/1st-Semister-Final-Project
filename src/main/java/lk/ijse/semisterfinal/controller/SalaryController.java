package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.semisterfinal.Tm.ItemTm;
import lk.ijse.semisterfinal.Tm.SalaryTm;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;
import lk.ijse.semisterfinal.dto.SalaryDTO;
import lk.ijse.semisterfinal.model.AddEmployeeModel;
import lk.ijse.semisterfinal.model.SalaryModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SalaryController {

    public AnchorPane root;
    public DatePicker date;
    public ComboBox <String> comEmpId;
    public TextField lblName;
    public TableView atendanceTm;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colDate;
    public TableColumn colSalary;
    public TableColumn colAction;
    public TextField salary;

    public void initialize() {
        loadEmployeeId();
    }
    private void tableListener() {
        SalaryTm.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);

        });
    }

    private void setData(SalaryTm row) {
        comEmpId.setValue(row.getEmployeeId());
        lblName.setText(row.getEmployeeName());
        txtItemPrice.setText(String.valueOf(row.getItemPrice()));
        comsupid.setValue(row.getSupplierId());

    }

    private void clearField() {
        txtItemCode.setText("");
        comsupid.setValue("");
        txtItemPrice.setText("");
        txtWarrantyPeriod.setText("");
        txtitemDetails.setText("");

    }

    public void AddSalaryOnAction(ActionEvent event) {
        double amount = Double.parseDouble(salary.getText());
        String id = (String) comEmpId.getValue();
        String Name = lblName.getText();
        LocalDate date1 = date.getValue();


        var dto = new SalaryDTO(amount, id, Name, date1);

        try {
            boolean isaddite = SalaryModel.addSalary(dto);
            if (isaddite) {
                new Alert(Alert.AlertType.CONFIRMATION, "Add Successful").show();
                //loadAllItem();
                //clearField();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void BackOnAction(ActionEvent event) {
    }

    private void loadEmployeeId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<AddEmployeeDTO> idList = AddEmployeeModel.getAllEmployee();

            for (AddEmployeeDTO dto : idList) {
                obList.add(dto.getEmployeeId());
            }

            comEmpId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void comEmpIdOnAction(ActionEvent event) {
        String id = (String) comEmpId.getValue();
        try {
            AddEmployeeDTO dto = AddEmployeeModel.searchEmployee(id);
            lblName.setText(dto.getEmployeeName());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

}
