package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.semisterfinal.Tm.CustomerTm;
import lk.ijse.semisterfinal.Tm.SupplierTm;
import lk.ijse.semisterfinal.dto.CusromerDTO;
import lk.ijse.semisterfinal.dto.SupplierDTO;
import lk.ijse.semisterfinal.model.CustomerModel;
import lk.ijse.semisterfinal.model.SupplierModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public TableColumn <?,?> tmqty;
    public TableColumn <?,?> tmSupMobile;
    public AnchorPane rood;
    public TableView <SupplierTm> supplierAddTable;


    public void initialize() {
        setCellValueFactory();
        loadAllSupplier();
        tableListener();
    }

    private void tableListener() {
        supplierAddTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
//            System.out.println(newValue);
            setData(newValue);
        });
    }

    private void setData(SupplierTm row) {
        txtSupId.setText(row.getSupId());
        txtSupName.setText(row.getSupName());
        txtsupItemName.setText(row.getSupItemName());
        txtSupQty.setText(String.valueOf(row.getSupqty()));
        txtSupMobile.setText(String.valueOf(row.getSupMobile()));
    }

    public void addSupplierOnAction(ActionEvent event) {
            String supId = txtSupId.getText();
            String supName = txtSupName.getText();
            String supItemName = txtsupItemName.getText();
            int supqty = Integer.parseInt(txtSupQty.getText());
            String supMobile = txtSupMobile.getText();

            var dto = new SupplierDTO(supId,supName,supItemName,supqty,supMobile);

            try {
                boolean addSup = SupplierModel.addSuppliers(dto);
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

        var dto = new SupplierDTO(id,name,itemName,itemQty,mobile);

        try {
            boolean isUpdated = SupplierModel.updateSupplier(dto);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier Add").show();
                setCellValueFactory();
                loadAllSupplier();
                clearField();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        tmSupId.setCellValueFactory(new PropertyValueFactory<>("SupId"));
        tmSupName.setCellValueFactory(new PropertyValueFactory<>("SupName"));
        supItemName.setCellValueFactory(new PropertyValueFactory<>("SupItemName"));
        tmqty.setCellValueFactory(new PropertyValueFactory<>("supqty"));
        tmSupMobile.setCellValueFactory(new PropertyValueFactory<>("supMobile"));

    }

    private void loadAllSupplier() {

        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<SupplierDTO> dtoList = SupplierModel.getAllSupplier();

            for (SupplierDTO dto : dtoList) {
                obList.add(
                        new SupplierTm(
                                dto.getSupId(),
                                dto.getSupName(),
                                dto.getSupItemName(),
                                dto.getSupqty(),
                                dto.getSupMobile()

                        )
                );
            }

            supplierAddTable.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

