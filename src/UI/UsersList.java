package UI;

import Model.User;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Sam Yager on 6/20/2018.
 */
public class UsersList {
    public static Scene buildUserList(Stage primaryStage, ArrayList<User> users){

        Button home = new Button("Home");
        home.setPadding(new Insets(10,284,10,284));
        home.setOnAction(e -> primaryStage.setScene(HomePage.buildHomePage(primaryStage)));

        TableColumn<User, String> name = new TableColumn<>("Name");
        name.setMinWidth(298);
        name.setStyle( "-fx-alignment: CENTER-LEFT;");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> points = new TableColumn<>("Points");
        points.setMinWidth(298);
        points.setStyle( "-fx-alignment: CENTER-RIGHT;");
        points.setCellValueFactory(new PropertyValueFactory<>("points"));

        TableView<User> userList = new TableView<>();
        userList.setStyle("-fx-table-cell-border-color: transparent;");
        userList.setItems(FXCollections.observableArrayList(users));
        userList.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
                primaryStage.setScene(Profile.buildProfile(userList.getSelectionModel().getSelectedItem(), primaryStage)));
        userList.getColumns().addAll(name, points);

        userList.setMaxHeight(400);
        userList.setMaxWidth(600);
        userList.setMinHeight(400);
        userList.setMinWidth(600);

        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.getChildren().addAll(userList, home);

        BorderPane pane = new BorderPane(content);
        content.setStyle("-fx-background-color: #C2DFff;");

        return new Scene(pane, 700, 500);
    }
}
