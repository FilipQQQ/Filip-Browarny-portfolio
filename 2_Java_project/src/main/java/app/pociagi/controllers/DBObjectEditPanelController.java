package app.pociagi.controllers;

import app.pociagi.SceneChanger;
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

import java.net.URL;
import java.util.ResourceBundle;

public class DBObjectEditPanelController implements Initializable {

    @FXML
    private ListView propertiesList;

    @FXML
    private TextField propertyValue;

    public void initialize(URL url, ResourceBundle rb) {
        propertiesList.getItems().addAll(AppData.getInstance().selectedObject.data.keySet());
        propertiesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                propertyValue.setText(
                        AppData.getInstance().selectedObject.data.get(
                            propertiesList.getSelectionModel().getSelectedItem()).toString());
            }
        });
        propertiesList.getSelectionModel().selectFirst();
    }

    @FXML
    private void saveButtonPushed(ActionEvent e) {
        AppData.getInstance().selectedObject.editObject(
                propertiesList.getSelectionModel().getSelectedItem().toString(),
                propertyValue.getText()
        );
    }

    @FXML
    private void goBackButtonPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "admin_panel.fxml");
    }

    @FXML
    private void goBackPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "admin_panel.fxml");
    }
}
