package Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.custom.impl.RegisterDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import model.Register;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class SignUpFormController {
    public JFXComboBox<String> select;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public JFXTextField txtTelephone;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public JFXPasswordField txtReEnterPassword;
    public AnchorPane SignUpForm;


    public void initialize(){
        select.getItems().setAll("Admin","Cashier");
    }

    /*public void signUpForm(ActionEvent actionEvent) {
        System.out.println("A");
        if(txtPassword.getText().equals(txtReEnterPassword.getText())){
            System.out.println("B");
        }
    }*/






    public void signUpForm(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if(txtPassword.getText().equals(txtReEnterPassword.getText())){

            if(!txtName.getText().equals("") && !txtAddress.getText().equals("") && !select.getSelectionModel().getSelectedItem().equals("null") && !txtEmail.getText().equals("") && !txtTelephone.getText().equals("") && !txtPassword.getText().equals("") && !txtReEnterPassword.getText().equals("") && !txtUserName.getText().equals("")){

                Register register = new Register(txtName.getText(),txtAddress.getText(),txtEmail.getText(),select.getSelectionModel().getSelectedItem(),txtTelephone.getText(),txtUserName.getText(),txtPassword.getText());

                if (new RegisterDAOImpl().saveRegister(register)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Register successfully", ButtonType.OK).show();
                    txtName.clear();
                    txtAddress.clear();
                    txtEmail.clear();
                    select.getSelectionModel().clearSelection();
                    txtAddress.clear();
                    txtTelephone.clear();
                    txtUserName.clear();
                    txtPassword.clear();
                    txtReEnterPassword.clear();


                }else{
                    new Alert(Alert.AlertType.CONFIRMATION, "Not Register details saved successfully", ButtonType.OK).show();
                }


            }else{
                new Alert(Alert.AlertType.CONFIRMATION, "please fill all fields and try again", ButtonType.OK).show();
            }
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, "Not a password", ButtonType.OK).show();
        }
    }

    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        SignUpForm.getChildren().clear();
        SignUpForm.getChildren().add(load);
    }
}
