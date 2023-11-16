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
        comsupid.setValue("");
        txtItemPrice.setText("");
        txtWarrantyPeriod.setText("");
        txtitemDetails.setText("");

    }

    public void AddItemOnAction(ActionEvent event) {
        String ItemCode = txtItemCode.getText();
        String ItemName = txtitemDetails.getText();
        double ItemPrice = Double.parseDouble(txtItemPrice.getText());
        String SupplierId = (String) comsupid.getValue();
        String WarrantyPeriod = txtWarrantyPeriod.getText();


        var dto = new ItemDTO(ItemCode,ItemName,ItemPrice,SupplierId,WarrantyPeriod);

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


