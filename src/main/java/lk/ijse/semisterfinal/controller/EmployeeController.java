package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lk.ijse.semisterfinal.Tm.EmployeeTm;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;
import lk.ijse.semisterfinal.model.AddEmployeeModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeController {

    public TextField txtemployeeId;
    public TextField txtEmployeeName;
    public TextField txtEmployeeGender;
    public TextField txtEmployeePhone;
    public TextField txtAddress;
    public DatePicker empDate;
    public TextField txtPossition;
    public TableColumn <?, ?>  tmid;
    public TableColumn <?, ?>  tmEmpName;
    public TableColumn <?, ?>  tmEmpGender;
    public TableColumn <?, ?>  tmEmpMobile;
    public TableColumn <?, ?>  tmEmpAddress;
    public TableColumn <?, ?>  tmStartDate;
    public TableColumn <?, ?>  tmEmpPossition;
    public TableView <EmployeeTm>  EmployeeTm;
    public AnchorPane root;

    public void initialize(){
        loadAllEmployee();
        clearField();
        setCellValueFactory();

    }

    private void setCellValueFactory() {
        tmid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tmEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tmEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tmEmpMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tmStartDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        //tmEmpGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tmEmpPossition.setCellValueFactory(new PropertyValueFactory<>("possition"));

    }

    private void clearField() {
        txtemployeeId.setText("");
        txtEmployeeName.setText("");
        txtAddress.setText("");
        txtEmployeePhone.setText("");
        //empDate.setValue(LocalDate.parse(""));
        txtEmployeeGender.setText("");
        txtPossition.setText("");

    }

    public void EmployeeAddOnAction(ActionEvent event) {
        String id = txtemployeeId.getText();
        String name = txtEmployeeName.getText();
        String address = txtAddress.getText();
        int tele = Integer.parseInt(txtEmployeePhone.getText());
        String date = String.valueOf(empDate.getValue());
        //String gender = txtEmployeeGender.getText();
        String position = txtPossition.getText();

        var dto = new AddEmployeeDTO(id,name,address,tele,date,position);

        try {
            boolean addSup= AddEmployeeModel.addEmployee(dto);
            if (addSup) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee is Added").show();
                loadAllEmployee();
                clearField();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void EmployeeUpdateOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/UpdateEmployee.fxml"))));
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                //loadAllEmployee();
            }
        });
        stage.centerOnScreen();
        stage.show();
    }

    public void EmployeeDeleteOnAction(ActionEvent event) {

    }

    private void loadAllEmployee() {
        var model = new AddEmployeeModel();

        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<AddEmployeeDTO> dtoList = model.getAllEmployee();

            for (AddEmployeeDTO dto : dtoList) {
                obList.add(
                        new EmployeeTm(
                                dto.getEmployeeId(),
                                dto.getEmployeeName(),
                                dto.getEmpAddress(),
                                dto.getEmployeePhone(),
                                dto.getEmpDate(),
                                dto.getEmpPosition()
                        )
                );
            }

            EmployeeTm.setItems((ObservableList<lk.ijse.semisterfinal.Tm.EmployeeTm>) obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
