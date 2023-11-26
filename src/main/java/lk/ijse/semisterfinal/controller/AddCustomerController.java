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

import java.sql.SQLException;
import java.util.List;

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
        String custName = txtCustName.getText();
        String custAddress = txtCustAddress.getText();
        String custMobile = txtCustMobile.getText();
        String custItemid = txtCustitemId.getText();
        String custPayment = txtCustPayment.getText();

        var dto = new CusromerDTO(custId,custName,custAddress,custMobile,custItemid,custPayment);

        try {
            boolean isadd= CustomerModel.AddCustomer(dto);
            if (isadd) {
                new Alert(Alert.AlertType.CONFIRMATION, "Add Successful").show();
                loadAllCustomer();
                clearField();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"already define customer Id").show();
        }
    }


    public void initialize() {
        setCellValueFactory();
        loadAllCustomer();
    }

    private void setCellValueFactory() {
        tbCid.setCellValueFactory(new PropertyValueFactory<>("txtCustId"));
        tbCname.setCellValueFactory(new PropertyValueFactory<>("txtCustName"));
        tbCaddress.setCellValueFactory(new PropertyValueFactory<>("txtCustAddress"));
        tbCmobile.setCellValueFactory(new PropertyValueFactory<>("txtCustMobile"));
        tbCpayment.setCellValueFactory(new PropertyValueFactory<>("txtCustPayment"));
        tbCitemId.setCellValueFactory(new PropertyValueFactory<>("txtCustitemId"));
    }

    private void loadAllCustomer() {
        var model = new CustomerModel();

        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<CusromerDTO> dtoList = model.getAllCustomer();

            for (CusromerDTO dto : dtoList) {
                obList.add(
                        new CustomerTm(
                                dto.getTxtCustId(),
                                dto.getTxtCustName(),
                                dto.getTxtCustAddress(),
                                dto.getTxtCustMobile(),
                                dto.getTxtCustPayment(),
                                dto.getTxtCustitemId()
                        )
                );
            }

            CustomerAddTable.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


