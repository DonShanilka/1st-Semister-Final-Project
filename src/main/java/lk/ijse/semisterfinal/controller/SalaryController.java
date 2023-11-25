package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public TableView <SalaryTm> atendanceTm;
    public TableColumn <?,?> colId;
    public TableColumn <?,?> colName;
    public TableColumn <?,?> colDate;
    public TableColumn <?,?> colSalary;
    public TableColumn <?,?> colAction;
    public TextField salary;

    public void initialize() {
        loadEmployeeId();
        clearField();
        tableListener();
        setCellValueFactory();
    }
    private void tableListener() {
        SalaryTm.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData((SalaryTm) newValue);

        });
    }

    private void setData(SalaryTm row) {
        comEmpId.setValue(row.getEmployeeId());
        lblName.setText(row.getEmployeeName());
        salary.setText(String.valueOf(row.getSalary()));
        date.setValue(LocalDate.parse(row.getDate()));

    }

    private void clearField() {
        comEmpId.setValue("");
        lblName.setText("");
        salary.setText("");
        //date.setValue(LocalDate.parse(""));

    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

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
