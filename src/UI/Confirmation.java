package UI;

import App.DatabaseReader;
import Model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Sam Yager on 6/22/2018.
 */
public class Confirmation {

    public static void confirmBox(String message, Stage primaryStage){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setHeight(250);
        window.setWidth(300);

        Label label = new Label(message);
        label.setAlignment(Pos.CENTER);

        Button yes = new Button("Yes");
        yes.setOnAction(e -> {
            primaryStage.setScene(HomePage.buildHomePage(primaryStage));
            window.close();
        });

        Button no = new Button("No");
        no.setOnAction(e -> window.close());

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(yes, no);

        VBox page = new VBox();
        page.setAlignment(Pos.CENTER);
        page.getChildren().addAll(label, buttons);

        BorderPane layout = new BorderPane(page);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void confirm(String message, Stage primaryStage, User user){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setHeight(250);
        window.setWidth(300);

        Label label = new Label(message);
        label.setAlignment(Pos.CENTER);

        Button yes = new Button("Admin's Profile");
        yes.setOnAction(e -> primaryStage.setScene(Profile.buildProfile(user, primaryStage)));

        Button no = new Button("Customer List");
        no.setOnAction(e -> UsersList.buildUserList(primaryStage, DatabaseReader.search()));

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(yes, no);

        VBox page = new VBox();
        page.setAlignment(Pos.CENTER);
        page.getChildren().addAll(label, buttons);

        BorderPane layout = new BorderPane(page);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
