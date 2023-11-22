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

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class UpdateEmployeeController {

    public ComboBox <?> updateEmpId;
    public TextField updateEmpaddress;
    public TextField updateEmpName;
    public TextField updateEmpMobile;
    public DatePicker updateEmpDate;
    public ComboBox <?> updateEmpGender;
    public TextField updateEmpPosition;

   /* private AddEmployeeModel employeeModel = new AddEmployeeModel();

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

    public void btnUpdateOnAction(ActionEvent actionEvent) {
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

    private boolean validateEmployee() {
        boolean isValidate = true;
        boolean name = Pattern.matches("[A-Za-z]{5,}", txtName.getText());
        if (!name){
            showErrorNotification("Invalid Customer Name", "The customer name you entered is invalid");
            isValidate = false;
        }
        boolean con = Pattern.matches("[0-9]{10,}",txtContact.getText());
        if (!con){
            showErrorNotification("Invalid Contact Number", "The contact number you entered is invalid");
            isValidate = false;
        }
        boolean NIC = Pattern.matches("^([0-9]{9}|[0-9]{12})$",txtNic.getText());
        if (!NIC){
            showErrorNotification("Invalid NIC", "The NUC Number you entered is invalid");
            isValidate = false;

        }
        boolean Job = Pattern.matches("[A-Za-z]{5,}",txtJob.getText());
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
        comboEmployee_id.setValue("");
        txtName.setText("");
        txtContact.setText("");
        txtNic.setText("");
        txtJob.setText("");
    }


    public void comboEmployee_idOnAction(ActionEvent actionEvent) {
        String id = comboEmployee_id.getValue();

        try{
            EmployeeDto dto = employeeModel.searchEmployee(id);
            txtName.setText(dto.getName());
            txtContact.setText(dto.getContact());
            txtNic.setText(dto.getNic());
            txtJob.setText(dto.getJob());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
*/
}
