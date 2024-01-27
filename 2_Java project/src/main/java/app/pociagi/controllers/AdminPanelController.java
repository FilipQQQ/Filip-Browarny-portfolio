package app.pociagi.controllers;
import app.pociagi.SceneChanger;
import app.pociagi.db.Finders.All.*;
import app.pociagi.db.Finders.Single.FindConnection;
import app.pociagi.db.Finders.Single.FindStation;
import app.pociagi.db.Objects.Connection;
import app.pociagi.db.Objects.DBObject;
import app.pociagi.db.Objects.Connection;
import app.pociagi.db.Objects.Discount;
import app.pociagi.db.Utils.DatabaseHandler;
import app.pociagi.utils.AppData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminPanelController implements Initializable {

    @FXML
    private Button ConnectionButton, stopsButton, userButton, addButton, editButton, deleteButton;

    @FXML
    private ListView actionsListView;

    private ArrayList<DBObject> objectList;

    @FXML
    private void connectionButtonPushed(ActionEvent e) {
        objectList = new ArrayList<DBObject>(AllFindConnection.getAll());
        prepareListData();
    }

    @FXML
    private void discountsButtonPushed(ActionEvent e) {
        objectList = new ArrayList<DBObject>(AllFindDiscount.getAll());
        prepareListData();
    }

    @FXML
    private void classesButtonPushed(ActionEvent e) {
        objectList = new ArrayList<DBObject>(AllFindSeatClass.getAll());
        prepareListData();
    }
    @FXML
    private void ticketsButtonPushed(ActionEvent e) {
        objectList = new ArrayList<DBObject>(AllFindTicket.getAll());
        prepareListData();
    }
    @FXML
    private void stopsButtonPushed(ActionEvent e) {
        objectList = new ArrayList<DBObject>(AllFindStation.getAll());
        //objectList = new ArrayList<DBObject>(AllFindDiscount.getAll());
        prepareListData();
    }
    @FXML
    private void usersButtonPushed(ActionEvent e) {
        objectList = new ArrayList<DBObject>(AllFindUser.getAll());
        System.out.println((objectList));
        prepareListData();
    }
    @FXML
    private void addButtonPushed(ActionEvent e) {
        DBObject selectedObject = objectList.get(actionsListView.getSelectionModel().getSelectedIndex());
        AppData.getInstance().selectedObject = selectedObject;
        if  (selectedObject instanceof Connection) {
            AppData.getInstance().connection = new Connection(null, null, null);
            SceneChanger.changeScene(e, "add_new_connection_panel.fxml");
        } else {
            SceneChanger.changeScene(e, "dbobject_add_panel.fxml");
        }
    }
    @FXML
    private void editButtonPushed(ActionEvent e) {
        DBObject selectedObject = objectList.get(actionsListView.getSelectionModel().getSelectedIndex());
        AppData.getInstance().selectedObject = selectedObject;
        if  (selectedObject instanceof Connection) {
            AppData.getInstance().connection = FindConnection.findByID(selectedObject.getID());
            SceneChanger.changeScene(e, "add_new_connection_panel.fxml");
        } else
        SceneChanger.changeScene(e, "dbobject_edit_panel.fxml");
    }
    @FXML
    private void deleteButtonPushed(ActionEvent e) {
        DBObject selectedObject = objectList.get(actionsListView.getSelectionModel().getSelectedIndex());
        if (selectedObject instanceof Connection) {
            DatabaseHandler handler = DatabaseHandler.getInstance();
            String sql = String.format("DELETE FROM STOPS WHERE CONNECTION_ID=%s", selectedObject.getID());
            handler.executeQuery(sql);
        }
        selectedObject.deleteObject();
        objectList.remove(actionsListView.getSelectionModel().getSelectedIndex());
        prepareListData();
    }

    @FXML
    private void goBackButtonPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "main_menuv2.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actionsListView.setVisible(false);
    }

    private void prepareListData() {
        ArrayList<String> names = new ArrayList<>();
        for (DBObject d : objectList) {
            String a = d.toString();
            if (d instanceof Connection) {
                a = a.concat(String.format("%s -> %s",
                        FindStation.findById(((Connection) d).getDepartureStationId()).getName(),
                        FindStation.findById(((Connection) d).getArrivalStationId()).getName()));
            }
            names.add(a);
        }
        actionsListView.getItems().setAll(names);
        actionsListView.getSelectionModel().selectFirst();
        actionsListView.setVisible(true);
    }
}
