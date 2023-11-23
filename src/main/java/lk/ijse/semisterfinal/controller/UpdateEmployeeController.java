package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;
import lk.ijse.semisterfinal.model.AddEmployeeModel;
import org.controlsfx.control.Notifications;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class UpdateEmployeeController {

    public ComboBox <String> updateEmpId;
    public TextField updateEmpaddress;
    public TextField updateEmpName;
    public TextField updateEmpMobile;
    public DatePicker updateEmpDate;
    public ComboBox <String> updateEmpGender;
    public TextField updateEmpPosition;

   private AddEmployeeModel employeeModel = new AddEmployeeModel();

    public void initialize() {
        setValue();

    }

    private void setValue() {
        ObservableList <String> obList = FXCollections.observableArrayList();

        try {
            List<AddEmployeeDTO> idList = employeeModel.getAllEmployee();

            for ( AddEmployeeDTO dto : idList) {
                obList.add(dto.getEmployeeId());
            }
            updateEmpId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private boolean validateEmployee() {
        boolean isValidate = true;
        boolean name = Pattern.matches("[A-Za-z]{5,}", updateEmpId.getValue());
        if (!name){
            showErrorNotification("Invalid Customer Name", "The customer name you entered is invalid");
            isValidate = false;
        }
        boolean con = Pattern.matches("[0-9]{10,}",updateEmpName.getText());
        if (!con){
            showErrorNotification("Invalid Contact Number", "The contact number you entered is invalid");
            isValidate = false;
        }
        boolean NIC = Pattern.matches("^([0-9]{9}|[0-9]{12})$",updateEmpMobile.getText());
        if (!NIC){
            showErrorNotification("Invalid NIC", "The NUC Number you entered is invalid");
            isValidate = false;

        }
        boolean Job = Pattern.matches("[A-Za-z]{5,}",updateEmpPosition.getText());
        if (!Job){
            showErrorNotification("Invalid job type", "The job type you entered is invalid");
            isValidate = false;
        }
        return isValidate;
    }

   private void showErrorNotification(String title, String text) {
        Notifications.create()
                .title(title)
                .text(text)
                .showError();
    }

    private void clearFileds() {
        updateEmpId.setValue("");
        updateEmpName.setText("");
        updateEmpaddress.setText("");
        updateEmpMobile.setText("");
        updateEmpDate.setValue(LocalDate.parse(""));
        updateEmpGender.setValue("");
        updateEmpPosition.setText("");
    }


    public void comboEmployee_idOnAction(ActionEvent actionEvent) {
        String id = updateEmpId.getValue();

        try{
            AddEmployeeDTO dto = AddEmployeeModel.searchEmployee(id);
            updateEmpName.setText(dto.getEmployeeName());
            updateEmpMobile.setText(String.valueOf(dto.getEmployeePhone()));
            updateEmpaddress.setText(dto.getEmpAddress());
            updateEmpPosition.setText(dto.getEmpPosition());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void BackOnAction(ActionEvent event) {
    }

    public void EmployeeSaveOnAction(ActionEvent event) {
        String id = (String) updateEmpId.getValue();
        String name = updateEmpName.getText();
        String address = updateEmpaddress.getText();
        int contact = Integer.parseInt(updateEmpMobile.getText());
        String date = String.valueOf(updateEmpDate.getValue());
        String gender = (String) updateEmpGender.getValue();
        String job = updateEmpPosition.getText();

        try{
            if (!validateEmployee()){
                return;
            }
            var dto = new AddEmployeeDTO(id,name,address,contact,date,gender,job);
            boolean isUpdate = employeeModel.updateEmployee(dto);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee is updated").show();
                clearFileds();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
