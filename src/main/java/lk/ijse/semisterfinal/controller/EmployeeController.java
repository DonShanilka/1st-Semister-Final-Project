package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.Tm.EmployeeTm;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;
import lk.ijse.semisterfinal.model.AddEmployeeModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    public TextField txtemployeeId;
    public TextField txtEmployeeName;
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
    public Label lblTotalEmployee;
    public TextField txtEmail;
    public TableColumn <?, ?> tmEmpJob;
    public TableColumn <?, ?> tmEmpEmail;

    public void initialize(){
        loadAllEmployee();
        clearField();
        setCellValueFactory();
        tableListener();

    }

    private void setCellValueFactory() {
        tmid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tmEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tmEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tmEmpMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tmStartDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tmEmpEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tmEmpJob.setCellValueFactory(new PropertyValueFactory<>("possition"));

    }

    private void clearField() {
        txtemployeeId.setText("");
        txtEmployeeName.setText("");
        txtAddress.setText("");
        txtEmployeePhone.setText("");
        txtEmail.setText("");
        txtPossition.setText("");
        //empDate.setValue(LocalDate.parse(""));

    }

    private void tableListener() {
        EmployeeTm.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(EmployeeTm row) {
        txtemployeeId.setText(row.getId());
        txtEmployeeName.setText(row.getName());
        txtAddress.setText(row.getAddress());
        txtEmployeePhone.setText(String.valueOf(row.getMobile()));
        txtPossition.setText(String.valueOf(row.getPossition()));
        txtEmail.setText(String.valueOf(row.getPossition()));
        empDate.setValue(LocalDate.parse(row.getDate()));
    }

    public void EmployeeAddOnAction(ActionEvent event) {
        String id = txtemployeeId.getText();
        String name = txtEmployeeName.getText();
        String address = txtAddress.getText();
        int tele = Integer.parseInt(txtEmployeePhone.getText());
        String date = String.valueOf(empDate.getValue());
        String email = txtEmail.getText();
        String position = txtPossition.getText();

        var dto = new AddEmployeeDTO(id,name,address,tele,date,email,position);

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
                loadAllEmployee();
            }
        });
        stage.centerOnScreen();
        stage.show();
    }

    public void EmployeeDeleteOnAction(ActionEvent event) {
        String id = txtemployeeId.getText();

        try {
            boolean isDeleted = AddEmployeeModel.deleteEmployee(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier has deleted!").show();
                loadAllEmployee();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
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
                                dto.getEmpPosition(),
                                dto.getEmail()
                        )
                );
            }

            EmployeeTm.setItems((ObservableList<lk.ijse.semisterfinal.Tm.EmployeeTm>) obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void totalItem() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT COUNT(employee_id) FROM employee";

        String totalid = null;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                totalid = resultSet.getString("COUNT(total_id)");
            }
            lblTotalEmployee.setText(totalid);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            totalItem();
            loadAllEmployee();
            clearField();
            setCellValueFactory();
            tableListener();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
