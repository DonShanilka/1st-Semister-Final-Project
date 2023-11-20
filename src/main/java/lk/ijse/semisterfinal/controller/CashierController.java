package lk.ijse.semisterfinal.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.semisterfinal.Tm.CashierTm;
import lk.ijse.semisterfinal.dto.CashiyerDTO;
import lk.ijse.semisterfinal.dto.CusromerDTO;
import lk.ijse.semisterfinal.dto.ItemDTO;
import lk.ijse.semisterfinal.model.CashiyerModel;
import lk.ijse.semisterfinal.model.CustomerModel;
import lk.ijse.semisterfinal.model.ItemModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CashierController{

    public AnchorPane pane;
    public Label lblOrderId;
    public Label lblOrderDate;
    public JFXComboBox cmbCustomerId;
    public Label lblCustomerName;
    public JFXComboBox cmbItemCode;
    public Label lblItemName;
    public Label lblUnitPrice;
    public Label lblQtyOnHand;
    public TextField txtQty;
    public TableView <?> tblOrderCart;
    public TableColumn <?,?> colItemCode;
    public TableColumn <?,?> colDescription;
    public TableColumn <?,?> colUnitPrice;
    public TableColumn <?,?> colTotal;
    public TableColumn <?,?> colAction;
    public JFXButton btnAddToCart;
    public Label lblNetTotal;

    private SortedList<Object> obList = FXCollections.observableArrayList().sorted();
    private ItemModel itemModel = new ItemModel();
    private CustomerModel customerModel = new CustomerModel();

    public void initialize(){
        //generateNextOrderId();
        setDate();
        loadCustomerId();
        loadServiceId();
        setCellValueFactory();
        // loadAllservice();
    }



    private void setCellValueFactory(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("Service_id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Service_name"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("remove"));
    }
    private <ObservableList> void loadServiceId() {
        ObservableList obList = (ObservableList) FXCollections.observableArrayList();

        try {
            List<ItemDTO> idList = itemModel.loadAllItems();

            for (ItemDTO dto: idList) {
                obList.add(dto.getItemCode());
            }

            cmbItemCode.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerId() {
        ObservableList<String>obList = FXCollections.observableArrayList();

        try {
            List<CusromerDTO> idList = customerModel.get();
            for (CusromerDTO dto : idList){
                obList.add(dto.getTxtCustId());
            }
            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextOrderId(){
        try {
            String orderId = CashiyerModel.generateNextOrderId();
            lblOrderId.setText(orderId);
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private void setDate(){
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }
    public void cmbCustomerOnAction(ActionEvent actionEvent) {
        String id = (String) cmbCustomerId.getValue();

        try {
            CusromerDTO guardianDto = CustomerModel.getAllCustomer(id);
            lblCustomerName.setText(guardianDto.getTxtCustName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbServiceOnAction(ActionEvent actionEvent) {
        String id = cmbService_id.getValue();

        try {
            ServiceDto serviceDto = serviceModel.searchService(id);
            lblService_name.setText(serviceDto.getName());
            lblAmount.setText(String.valueOf(serviceDto.getAmount()));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {

        String service_id  = cmbService_id.getValue();
        String service_name = lblService_name.getText();
        double amount = Double.parseDouble(lblAmount.getText());

       /* Button btn = new Button("Remove");
        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);*/


        if (!obList.isEmpty()) {
            for (int i = 0; i < tblOrder.getItems().size(); i++) {
                if (colSer_Id.getCellData(i).equals(service_id)) {
                    double S_amount = (double) colAmount.getCellData(i);

                    tblOrder.refresh();
                    return;
                }
            }
        }
        var cartTm = new CartTm(service_id,service_name,amount);
        obList.add(cartTm);
        calcTotal();
        tblOrder.setItems(obList);
    }
    private void calcTotal(){
        double total = 0;
        for (int i = 0 ; i < tblOrder.getItems().size();i++){
            total +=(double)colAmount.getCellData(i);
        }
        lblFullAmount.setText(String.valueOf(total));
    }
    private void setRemoveBtnAction(Button btn) {
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
//        String orderId = lblOrderId.getText();
//        LocalDate date = LocalDate.parse(lblOrderDate.getText());
//        String service_id = cmbService_id.getValue();
//
//        List<CartTm> cartTmList = new ArrayList<>();
//        for (int i = 0; i < tblOrder.getItems().size(); i++) {
//            CartTm cartTm = obList.get(i);
//
//            cartTmList.add(cartTm);
//        }

        var orderDto = new OrderDto(txtOrderId.getText(), lblOrderDate.getText(), cmbGuardian_Id.getValue());
        var oService = new OrderServiceDto(txtOrderId.getText(), lblOrderDate.getText(), cmbService_id.getValue());

        try {
            if (placeOrder.placeAOrder(orderDto, oService)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order saved!!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbItemOnAction(ActionEvent event) {
    }

    public void txtQtyOnAction(ActionEvent event) {
    }

    //public void btnAddToCartOnAction(ActionEvent event) {
    //}
}