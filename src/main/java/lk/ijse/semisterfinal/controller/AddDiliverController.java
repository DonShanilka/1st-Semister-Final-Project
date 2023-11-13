package lk.ijse.semisterfinal.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class AddDiliverController implements Initializable {
    public AnchorPane rootNode;
    public BorderPane borderPane;
    public AnchorPane sliderAnchor;
    public TextField txtOrderId;
    public TextField txtOrderMobile;
    public TextField txtItemId;
    public TextField txtOrderAddress;
    public TextField txtCustitemIdOrder;
    public TableView orderTable;
    public TableColumn tbOrderId;
    public TableColumn tbOrderName;
    public TableColumn tbOrderAddress;
    public TableColumn tbOrderMobile;
    public TableColumn tbOrderPayment;
    public TableColumn tbOrderItemId;
    public Label addOrder;
    public Label backOrder;
    @FXML
    private Label back;

    @FXML
    private AnchorPane borderpane;

    @FXML
    private Label get;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        borderpane.setTranslateX(-176);

        get.setOnMouseClicked(mouseEvent -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(borderpane);

            slide.setToX(0);
            slide.play();

            borderpane.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e)-> {
                get.setVisible(false);
                back.setVisible(true);
            });

        });

        back.setOnMouseClicked(mouseEvent -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(borderpane);

            slide.setToX(-176);
            slide.play();

            borderpane.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                get.setVisible(true);
                back.setVisible(false);
            });

        });
    }

    public void OrderDeleteOnAction(ActionEvent event) {
    }

    public void OrderAddOnAction(ActionEvent event) {
    }

    public void OrderUpdateOnAction(ActionEvent event) {
    }
}
