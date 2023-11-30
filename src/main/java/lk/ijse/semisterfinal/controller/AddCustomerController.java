package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import lk.ijse.semisterfinal.Tm.CustomerTm;
import lk.ijse.semisterfinal.dto.CusromerDTO;
import lk.ijse.semisterfinal.model.CustomerModel;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import static java.awt.SystemColor.text;

public class AddCustomerController {

    public TableView <CustomerTm> CustomerAddTable;
    public TableColumn <?, ?> tbCid;
    public TableColumn <?, ?> tbCname;
    public TableColumn <?, ?> tbCaddress;
    public TableColumn <?, ?> tbCmobile;
    public TableColumn <?, ?> tbCpayment;
    public TableColumn <?, ?> tbCitemId;
    @FXML
    private BorderPane borderPane;

    @FXML
    private Label newCustomer;

    @FXML
    private Label newCustomerBack;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private AnchorPane sliderAnchor;


    public TextField txtCustMobile;
    public TextField txtCustName;
    public TextField txtCustPayment;
    public TextField txtCustAddress;
    public TextField txtCustitemId;
    public TextField txtCustId;

    private void clearField() {
        txtCustId.setText("");
        txtCustName.setText("");
        txtCustAddress.setText("");
        txtCustMobile.setText("");
        txtCustPayment.setText("");
        txtCustitemId.setText("");

    }

    public void CustomerUpdateOnAction(ActionEvent event) {
        
    }

    public void CustomerDeleteOnAction(ActionEvent event) {
        String id = txtCustId.getText();

        try{
            boolean isDelete = CustomerModel.deleteCustomer(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer deleted").show();
                loadAllCustomer();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void CustomerAddOnAction(ActionEvent event) {
        String custId = txtCustId.getText();
        String custAddress = txtCustAddress.getText();
        String custName = txtCustName.getText();
        String custMobile = txtCustMobile.getText();
        String custItemid = txtCustitemId.getText();
        String custPayment = txtCustPayment.getText();

        try {
            if (!validateCustomer()){
                return;
            }
            var dto = new CusromerDTO(custId,custName,custAddress,custMobile,custItemid,custPayment);
            boolean isadd= CustomerModel.AddCustomer(dto);
            if (isadd){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer is Added").show();
                loadAllCustomer();
                //clearFileds();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void initialize() {
        setCellValueFactory();
        loadAllCustomer();
    }

    private void setCellValueFactory() {
        tbCid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbCname.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbCaddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tbCmobile.setCellValueFactory(new PropertyValueFactory<>("tel"));
        tbCpayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        tbCitemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));

    }

    private void loadAllCustomer() {
        var model = new CustomerModel();

        ObservableList <CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List <CusromerDTO> dtoList = model.getAllCustomer();

            for (CusromerDTO dto : dtoList) {
                obList.add(
                        new CustomerTm(
                                dto.getTxtCustId(),
                                dto.getTxtCustName(),
                                dto.getTxtCustAddress(),
                                dto.getTxtCustMobile(),
                                dto.getTxtCustitemId(),
                                dto.getTxtCustPayment()
                        )
                );
            }

            CustomerAddTable.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateCustomer() {
        boolean isValidate = true;
        /*boolean id = Pattern.matches("[0-9]{0,}", txtCustId.getText());
        if (!id){
            showErrorNotification("Invalid Employee Id", "The Employee Id you entered is invalid");
            isValidate = false;
        }*/
        boolean address = Pattern.matches("[A-Za-z]{0,}",txtCustAddress.getText());
        if (!address){
            showErrorNotification("Invalid Address", "The Address you entered is invalid");
            isValidate = false;
        }
        boolean mobile = Pattern.matches("^([0-9]{9}|[0-9]{10})$",txtCustMobile.getText());
        if (!mobile){
            showErrorNotification("Invalid Mobile", "The Mobile Number you entered is invalid");
            isValidate = false;

        }
        return isValidate;
    }

    private void showErrorNotification(String title, String txtt) {
        Notifications.create()
                .title(title)
                .text(String.valueOf(text))
                .showError();
    }

}


