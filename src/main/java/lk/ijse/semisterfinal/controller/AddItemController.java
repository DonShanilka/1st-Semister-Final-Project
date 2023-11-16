package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.ijse.semisterfinal.Tm.ItemTm;
import lk.ijse.semisterfinal.dto.ItemDTO;
import lk.ijse.semisterfinal.model.ItemModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class AddItemController {
    public TextField txtItemCode;
    public TextField txtItemPrice;
    public TextField txtWarrantyPeriod;
    public TextArea txtitemDetails;
    public ComboBox comsupid;

    public Pane root;
    public TableView<lk.ijse.semisterfinal.Tm.ItemTm> ItemTm;
    public TableColumn <?,?> tmItemCode;
    public TableColumn <?,?> tmItemDetails;
    public TableColumn <?,?> tmItemPrice;
    public TableColumn <?,?> tmSupplierId;
    public TableColumn <?,?> tmWarranty;

    public void initialize() {
        setCellValueFactory();
        tableListener();
        loadAllItem();
    }

    private void tableListener() {
        ItemTm.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);

        });
    }

    private void setData(ItemTm row) {
        txtItemCode.setText(row.getItemCode());
        txtitemDetails.setText(row.getItemDetails());
        txtItemPrice.setText(String.valueOf(row.getItemPrice()));
        comsupid.setValue(row.getSupplierId());
        txtWarrantyPeriod.setText(row.getWarrantyPeriod());
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


    private void loadAllItem() {

        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<ItemDTO> dtoList = ItemModel.getAllItem();

            for (ItemDTO dto : dtoList) {
                obList.add(
                        new ItemTm(
                                dto.getItemCode(),
                                dto.getItemDetails(),
                                dto.getItemPrice(),
                                dto.getSupplierId(),
                                dto.getWarrantyPeriod()

                        ));
            }

            ItemTm.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        tmItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        tmItemDetails.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        tmItemPrice.setCellValueFactory(new PropertyValueFactory<>("ItemPrice"));
        tmSupplierId.setCellValueFactory(new PropertyValueFactory<>("SupplierId"));
        tmWarranty.setCellValueFactory(new PropertyValueFactory<>("WarrantyPeriod"));

    }

    public void UpdateOnAction(ActionEvent event) {
    }

    public void deleteOnAction(ActionEvent event) {
    }
}


