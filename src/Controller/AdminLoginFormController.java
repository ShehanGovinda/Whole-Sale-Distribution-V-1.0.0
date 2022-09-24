package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginFormController {
    public AnchorPane AdminLoginForm;
    public JFXButton btnAdmin;
    public JFXButton btnAdminLogin;
    static String firstnameofadmin;
    public JFXTextField txtUserName;
    // public JFXTextField txtPassword;
    public JFXPasswordField txtPassword1;

    public void initialize() {

        btnAdmin.setDisable(true);
    }

    public void cashierLoginForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CashierLoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminLoginForm.getChildren().clear();
        AdminLoginForm.getChildren().add(load);
    }

    public void goToSignUpForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/SignUpForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminLoginForm.getChildren().clear();
        AdminLoginForm.getChildren().add(load);
    }


    public void goToAdminDashBoardFormController(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        boolean include = getUserInfo("Admin");

        if (include == true) {
            URL resource = getClass().getResource("../views/AdminDashBoardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            AdminLoginForm.getChildren().clear();
            AdminLoginForm.getChildren().add(load);
        } else {
            new Alert(Alert.AlertType.WARNING, "User Name or Password is incorrect..please Try again..!").show();
        }
    }

    public boolean getUserInfo(String type) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Register` WHERE userType='" + type + "'");
        ResultSet rst = stm.executeQuery();

        while (rst.next()) {
            if (rst.getString(6).equals(txtUserName.getText()) && rst.getString(7).equals(txtPassword1.getText())) {
                //txtUserName =rst.getString(1);
                //firstnameofcashier=rst.getString(1);//set karanawa name eka ss kiyana eka admin dana onama ekak
                return true;

            }
        }
        return false;
    }


}
