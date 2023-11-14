package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.ijse.semisterfinal.Tm.SupplierTm;
import lk.ijse.semisterfinal.dto.CusromerDTO;
import lk.ijse.semisterfinal.dto.ItemDTO;
import lk.ijse.semisterfinal.dto.SupplierDTO;
import lk.ijse.semisterfinal.model.ItemModel;
import lk.ijse.semisterfinal.model.SupplierModel;

import java.sql.SQLException;
import java.util.List;

public class AddItemController {
    public TextField txtItemCode;
    public TextField txtSupplierId;
    public TextField txtItemPrice;
    public TextField txtWarrantyPeriod;
    public TextArea txtitemDetails;
    public ComboBox comsupid;

    private ItemModel itemModel = new ItemModel();

    public void initialize() {
        loadSupplier();
    }

    private void clearField() {
        txtItemCode.setText("");
        txtSupplierId.setText("");
        txtItemPrice.setText("");
        txtWarrantyPeriod.setText("");
        txtitemDetails.setText("");

    }

    public void AddItemOnAction(ActionEvent event) {
        String code = txtItemCode.getText();
        double itemPrice = Double.parseDouble(txtItemPrice.getText());
        String details = txtitemDetails.getText();
        String supplierid = txtSupplierId.getText();
        String warranty = txtWarrantyPeriod.getText();


        var dto = new ItemDTO(code,itemPrice,details,supplierid,warranty);

        try {
            boolean isaddite = ItemModel.addItem(dto);
            if (isaddite) {
                new Alert(Alert.AlertType.CONFIRMATION, "Add Successful").show();
                clearField();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void cmbsupidOnAction(ActionEvent event) {

    }

    private void loadSupplier() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SupplierDTO> itemDtos = SupplierModel.loadAllSupplier();

            for (SupplierDTO dto : itemDtos) {
                obList.add(dto.getSupId());
            }
            comsupid.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


