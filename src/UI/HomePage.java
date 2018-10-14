package UI;

import App.DatabaseReader;
import Model.User;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Sam Yager on 6/20/2018.
 */
public class HomePage{

    public static Scene buildHomePage(Stage primaryStage) {

        //Search Bar
        TextField search = new TextField();
        search.setPromptText("Search");

        //Search Bar Icon and Button
        File path1 = new File("assets/Search.png");
        //System.out.println(path.getAbsolutePath());
        Image icon = new Image(path1.toURI().toString());
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(17);
        iconView.setFitWidth(15);
        Button searchButton = new Button();
        searchButton.setOnAction(e -> {
            if(search.getText().matches("(([A-z])\\w+ ([A-z])\\w+)|([A-z])\\w+")) {
                primaryStage.setScene(UsersList.buildUserList(primaryStage, DatabaseReader.search(search.getText())));
            } else {
                AlertBox.alertBox("'" + search.getText() + "' is not a Name");
            }
        });
        searchButton.setGraphic(iconView);

        HBox searchBar = new HBox();
        searchBar.getChildren().addAll(search, searchButton);
        searchBar.setAlignment(Pos.CENTER);

        Button viewAll = new Button("View All Customers");
        viewAll.setAlignment(Pos.CENTER);
        viewAll.setPrefWidth(180);
        viewAll.setOnAction(e -> primaryStage.setScene(UsersList.buildUserList(primaryStage, DatabaseReader.search())));

        File path2 = new File("assets/Watermark.png");
        //System.out.println(path.getAbsolutePath());
        Image image = new Image(path2.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(450);
        imageView.setFitWidth(450);
        imageView.setPreserveRatio(true);

        VBox home = new VBox();
        home.getChildren().addAll(imageView, searchBar, viewAll);
        home.setAlignment(Pos.CENTER);

        Pane borderPane = new BorderPane(home);
        borderPane.setStyle("-fx-background-color: #C2DFff;");

        return new Scene(borderPane, 700, 500);
    }
}
