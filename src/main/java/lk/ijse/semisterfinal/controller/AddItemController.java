package lk.ijse.semisterfinal.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lk.ijse.semisterfinal.dto.ItemDTO;
import lk.ijse.semisterfinal.model.ItemModel;

import java.sql.SQLException;

public class AddItemController {
    public TextField txtItemCode;
    public TextField txtSupplierId;
    public TextField txtItemPrice;
    public TextField txtWarrantyPeriod;
    public TextArea txtitemDetails;

    private void clearField() {
        txtItemCode.setText("");
        txtSupplierId.setText("");
        txtItemPrice.setText("");
        txtWarrantyPeriod.setText("");
        txtitemDetails.setText("");

    }

    public void AddItemOnAction(ActionEvent event) {
        String code = txtItemCode.getText();
        String details = txtitemDetails.getText();
        String itemPrice = txtItemPrice.getText();
        String supplierid = txtSupplierId.getText();
        String warranty = txtWarrantyPeriod.getText();


        var dto = new ItemDTO(code,details,itemPrice,supplierid,warranty);

        try {
            boolean isaddite= ItemModel.addItem(dto);
            if (isaddite) {
                new Alert(Alert.AlertType.CONFIRMATION, "Add Successful").show();
                clearField();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}


