package me.reidj.takiwadai.scene.registration;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.reidj.takiwadai.App;
import me.reidj.takiwadai.database.DbUtil;
import me.reidj.takiwadai.scene.AbstractScene;
import me.reidj.takiwadai.user.RoleType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

public class RegistrationScene extends AbstractScene {

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField secondName;

    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private TextField surname;

    public RegistrationScene() {

    }

    public RegistrationScene(Stage stage) {
        super("/fxml/registration/registration.fxml", stage);
        try (Connection connection = DbUtil.getDataSource().getConnection()) {
            Statement st = connection.createStatement();
            st.executeUpdate(DbUtil.CREATE_TABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void processRegistration() {
        if (surname.getText().isEmpty() || password.getText().isEmpty() || name.getText().isEmpty()
                || email.getText().isEmpty() || confirmPassword.getText().isEmpty() || secondName.getText().isEmpty()) {
            errorAlert("Пожалуйста, заполните все поля", "Поля не могут быть пустыми!");
            return;
        }
        try (Connection connection = DbUtil.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DbUtil.CREATE_USER);
            statement.setString(1, UUID.randomUUID().toString());
            statement.setString(2, name.getText());
            statement.setString(3, surname.getText());
            statement.setString(4, secondName.getText());
            statement.setString(5, email.getText());
            statement.setString(6, password.getText());
            statement.setString(7, RoleType.USER.name());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void move() throws IOException {
        App.getApp().getMainScene().getScene().setRoot(App.getApp().getMainScene().getParent());
    }
}
