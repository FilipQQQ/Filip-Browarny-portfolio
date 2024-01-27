package app.pociagi;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {

    public static void changeScene(ActionEvent event, String fxml) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(SceneChanger.class.getResource(fxml));
            root = loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
