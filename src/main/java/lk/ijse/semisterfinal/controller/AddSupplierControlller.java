package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.semisterfinal.Tm.CustomerTm;
import lk.ijse.semisterfinal.Tm.SupplierTm;
import lk.ijse.semisterfinal.dto.CusromerDTO;
import lk.ijse.semisterfinal.dto.SupplierDTO;
import lk.ijse.semisterfinal.model.CustomerModel;
import lk.ijse.semisterfinal.model.SupplierModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AddSupplierControlller {
    public TextField txtSupName;
    public TextField txtSupId;
    public TextField txtsupItemName;
    public TableColumn <?,?> tmSupId;
    public TableColumn <?,?> tmSupName;
    public TableColumn <?,?> supItemName;
    public TextField txtSupQty;
    public TextField txtSupMobile;
    public DatePicker txtSupDate;
    public TableColumn <?,?> tmqty;
    public TableColumn <?,?> tmDate;
    public TableColumn <?,?> tmSupMobile;
    public AnchorPane rood;
    public TableView <SupplierTm> supplierAddTable;


    public void addSupplierOnAction(ActionEvent event) {
            String id = txtSupId.getText();
            String name = txtSupName.getText();
            String itemName = txtsupItemName.getText();
            int itemQty = Integer.parseInt(txtSupQty.getText());
            String mobile = txtSupMobile.getText();
            LocalDate date = txtSupDate.getValue();

            var dto = new SupplierDTO(id,name,itemName,itemQty,mobile,date);

            try {
                boolean addSup= SupplierModel.addSuppliers(dto);
                if (addSup) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added").show();
                    clearField();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"Try Again").show();
            }
        }


    private void clearField() {
        txtSupId.setText("");
        txtSupName.setText("");
        txtsupItemName.setText("");
        txtSupQty.setText("");
        txtSupDate.setValue(LocalDate.parse(""));
        txtSupMobile.setText("");

    }

    public void deleteSupplierOnAction(ActionEvent event) {
        String id = txtSupId.getText();

        try {
            boolean isDeleted = SupplierModel.deleteSupplier(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier has deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void updateSupplierOnAction(ActionEvent event) {
        String id = txtSupId.getText();
        String name = txtSupName.getText();
        String itemName = txtsupItemName.getText();
        int itemQty = Integer.parseInt(txtSupQty.getText());
        String mobile = txtSupMobile.getText();
        LocalDate date = txtSupDate.getValue();

        var dto = new SupplierDTO(id,name,itemName,itemQty,mobile,date);

        try {
            boolean isUpdated = SupplierModel.updateSupplier(dto);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated!").show();
                clearField();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllSupplier() {
        var model = new CustomerModel();

        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<SupplierDTO> dtoList = model getAllSupplier.getall();

            for (SupplierDTO dto : dtoList) {
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

            supplierAddTable.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

