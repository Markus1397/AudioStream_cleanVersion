/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.audiostream.client.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Eva
 */
public class LeftPart {

    Button settingsButton = new Button("Settings");
    Label serverName = new Label("No server connected");
    Button disconnect = new Button("Connect");
    TableColumn servernames = new TableColumn("Servers");
    final ObservableList<Server> data = FXCollections.observableArrayList();

    public void setCurrentServer(Server currentServer) {
        this.currentServer = currentServer;
    }
    TableView servers = new TableView(data);
    Server currentServer = null;

    public Server getCurrentServer() {
        return currentServer;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public Label getServerName() {
        return serverName;
    }

    public void setServerName(Label serverName) {
        this.serverName = serverName;
    }

    public Label getServernameLabel() {
        return servernameLabel;
    }

    public Button getDisconnect() {
        return disconnect;
    }
    Label servernameLabel;

    public ObservableList<Server> getData() {
        return data;
    }

    public LeftPart(BorderPane root) {
        VBox left = new VBox();
        servers.getColumns().add(servernames);
        servers.setItems(data);
        servernames.setCellValueFactory(new PropertyValueFactory<>("hostname"));
        servernames.setPrefWidth(120);
        servernameLabel = new Label("Current");
        Label servernameLabel2 = new Label("Server: ");
        Label nothing = new Label();
        Label nothing2 = new Label();
        left.getChildren().addAll(servernameLabel, servernameLabel2, serverName, servers, nothing2, settingsButton, nothing, disconnect);
        root.setLeft(left);
        left.setAlignment(Pos.TOP_CENTER);
        left.setPadding(new Insets(10, 10, 10, 10));
        left.setPrefWidth(150);
        left.setMaxWidth(150);
        left.setId("left");
        servernameLabel.setId("servernamelabel");
        serverName.setId("servername");
        servernameLabel2.setId("servernamelabel2");
        settingsButton.setId("settingsbutton");
        disconnect.setId("disconnect");
        servernameLabel.setPadding(new Insets(20, 20, 0, 20));
        servernameLabel2.setPadding(new Insets(0, 20, 20, 20));
        servers.setPadding(new Insets(10, 0, 20, 0));
        settingsButton.setPadding(new Insets(20, 0, 0, 0));
        serverName.setPadding(new Insets(0, 20, 10, 20));
        disconnect.setDisable(true);
    }

    public TableView getServers() {
        return servers;
    }
}
