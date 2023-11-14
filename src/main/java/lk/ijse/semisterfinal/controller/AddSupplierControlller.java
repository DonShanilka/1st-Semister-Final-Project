package lk.ijse.semisterfinal.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import lk.ijse.semisterfinal.dto.EmployeeLogDTO;
import lk.ijse.semisterfinal.dto.SupplierDTO;
import lk.ijse.semisterfinal.model.EmployeeLogModel;
import lk.ijse.semisterfinal.model.SupplierModel;

import java.sql.SQLException;

public class AddSupplierControlller {
    public TextField txtSupName;
    public TextField txtSupId;
    public TextField txtsupItemName;
    public TableColumn tmSupId;
    public TableColumn tmSupName;
    public TableColumn supItemName;
    public TextField txtSupQty;
    public TextField txtSupMobile;
    public DatePicker txtSupDate;


    public void addSupplierOnAction(ActionEvent event) {
            String id = txtSupId.getText();
            String name = txtSupName.getText();
            String itemName = txtsupItemName.getText();

            var dto = new SupplierDTO(id,name,itemName);

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

    }

    }

