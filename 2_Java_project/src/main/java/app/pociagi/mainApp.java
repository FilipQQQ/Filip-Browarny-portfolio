package app.pociagi;

import app.pociagi.db.Finders.Single.FindRide;
import app.pociagi.db.Objects.ConnectionStop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class mainApp extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login_menu.fxml"));
        stage.setTitle("Hello!");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) throws ParseException {
        launch();
    }
}