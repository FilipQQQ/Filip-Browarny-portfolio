package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import app.pociagi.db.Objects.DBObject;
import app.pociagi.utils.AppData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class DBObjectAddPanelController implements Initializable {

    @FXML
    private ListView propertiesList;

    @FXML
    private TextField propertyValue;

    private DBObject object;

    public void initialize(URL url, ResourceBundle rb) {
        object = new DBObject();
        object.table = AppData.getInstance().selectedObject.table;
        propertiesList.getItems().addAll(AppData.getInstance().selectedObject.data.keySet());
        propertiesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (object.data.containsKey(propertiesList.getSelectionModel().getSelectedItem().toString())) {
                    propertyValue.setText(
                            object.data.get(
                                    propertiesList.getSelectionModel().getSelectedItem()).toString());
                }
                else propertyValue.setText("");
            }
        });
        propertiesList.getSelectionModel().selectFirst();
    }

    @FXML
    private void saveButtonPushed(ActionEvent e) {
        object.data.put(propertiesList.getSelectionModel().getSelectedItem().toString(),
                propertyValue.getText());
    }

    @FXML
    private void goBackButtonPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "admin_panel.fxml");
    }

    @FXML
    private void addPushed(ActionEvent e) {
        object.pushToDB();
        SceneChanger.changeScene(e, "admin_panel.fxml");
    }

    @FXML
    private void keyTyped(KeyEvent e) {
        object.data.put(propertiesList.getSelectionModel().getSelectedItem().toString(),
            propertyValue.getText());
    }
}
