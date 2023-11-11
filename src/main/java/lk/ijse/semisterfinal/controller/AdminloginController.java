package lk.ijse.semisterfinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.semisterfinal.DB.DbConnetion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class AdminloginController {
    public Connection connection;
    //private DbConnetion dbConnetion;

    public AnchorPane anroot;
    public TextField txtname;
    public TextField txtpassword;

    @FXML
    void AdminBackOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/selectroll.fxml"));
        anroot.getChildren().clear();
        anroot.getChildren().add(load);
    }

    private void clearField() {
        txtname.setText("");
        txtpassword.setText("");
    }


    @FXML
    void AdminLoginOnaction(MouseEvent event) throws IOException, SQLException{
        String username = txtname.getText();
        String password = txtpassword.getText();
        Connection connection = DbConnetion.getInstance().getConnection();
        String sql = "SELECT * FROM admin WHERE admin_name = ? AND admin_password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            if(username.isEmpty() || password.isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Empty").show();
                return;
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Parent rootNode = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/adminMainDashbord.fxml")));
                Scene scene = new Scene(rootNode);
                Stage primaryStage = (Stage) this.anroot.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.setTitle("Admin Dashboard");
            } else {
                new Alert(Alert.AlertType.ERROR, "oops! credentials are wrong!").show();
                clearField();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            clearField();
        }
    }


    public void HyperOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/adminregister.fxml"));
        anroot.getChildren().clear();
        anroot.getChildren().add(load);
    }

    public void AdminLoginOnAction(ActionEvent event) {
    }
}
