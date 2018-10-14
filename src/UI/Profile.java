package UI;

import App.DatabaseReader;
import Model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Sam Yager on 6/20/2018.
 */
public class Profile {

    private static boolean changes = false;

    public static Scene buildProfile(User user, Stage primaryStage){

        Label name = new Label("Name: " + user.getName());
        Label email = new Label("Email: " + user.getEmail());
        Label points = new Label("Points: " + user.getPoints());
        Label add = new Label("Add Points: ");
        add.setPadding(new Insets(5,22,0,0));
        Label redeem = new Label("Redeem Points: ");
        redeem.setPadding(new Insets(5,0,0,0));

        File path1 = new File("assets/Check.png");
        //System.out.println(path.getAbsolutePath());
        Image icon = new Image(path1.toURI().toString());
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(10);
        iconView.setFitWidth(10);

        File path2 = new File("assets/Check.png");
        //System.out.println(path.getAbsolutePath());
        Image icon2 = new Image(path2.toURI().toString());
        ImageView iconView2 = new ImageView(icon2);
        iconView2.setFitHeight(10);
        iconView2.setFitWidth(10);

        TextField redeemField = new TextField();
        redeemField.setPromptText("Number of Points");
        Button redeemCheck = new Button();
        redeemCheck.setOnAction(e -> {
            String pointsString = redeemField.getText();
            if (pointsString.matches("-?\\d+")){
                int userPoints = Integer.parseInt(pointsString);
                if(userPoints <= user.getPoints()) {
                    user.redeemPoints(Integer.parseInt(pointsString));
                    changes = true;
                    primaryStage.setScene(Profile.buildProfile(user, primaryStage));
                } else {
                    AlertBox.alertBox("This user doesn't have that many points");
                }
            } else {
                AlertBox.alertBox("'" + pointsString + "' is not a number");
            }
        });
        redeemCheck.setGraphic(iconView);

        HBox redeemPoints = new HBox();
        redeemPoints.getChildren().addAll(redeem, redeemField, redeemCheck);

        TextField addField = new TextField();
        addField.setPromptText("Number of Points");
        Button addCheck = new Button();
        addCheck.setOnAction(e -> {
            String pointsString = addField.getText();
            if (pointsString.matches("-?\\d+")){
                user.addRewards(Integer.parseInt(pointsString));
                changes = true;
                primaryStage.setScene(Profile.buildProfile(user, primaryStage));
            } else {
                AlertBox.alertBox("'" + pointsString + "' is not a number");
            }
        });
        addCheck.setGraphic(iconView2);

        HBox addPoints = new HBox();
        addPoints.getChildren().addAll(add, addField, addCheck);

        Button goHome = new Button("Home");
        goHome.setOnAction(e -> {
            if(changes) {
                e.consume();
                Confirmation.confirmBox("Are you sure you want to leave without saving?", primaryStage);
            } else {
                primaryStage.setScene(HomePage.buildHomePage(primaryStage));
            }
        });

        Button save = new Button("Save");
        save.setOnAction(e -> {
            if(changes) {
                changes = false;
                DatabaseReader.updateUser(user);
            }
        });

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(50,0,0,0));
        buttons.getChildren().addAll(save, goHome);

        VBox pointButtons = new VBox();
        pointButtons.setAlignment(Pos.CENTER_RIGHT);
        pointButtons.setPadding(new Insets(0,0,0,100));
        pointButtons.getChildren().addAll(addPoints, redeemPoints);

        VBox userInfo = new VBox();
        userInfo.setAlignment(Pos.CENTER_LEFT);
        userInfo.setPadding(new Insets(10, 0, 10, 0));
        userInfo.getChildren().addAll(name, email, points);

        HBox profileTop = new HBox();
        profileTop.getChildren().addAll(userInfo, pointButtons);
        profileTop.setPrefWidth(700);
        profileTop.setAlignment(Pos.CENTER);

        VBox profile = new VBox();
        profile.setAlignment(Pos.CENTER);
        profile.setPrefWidth(700);
        profile.getChildren().addAll(profileTop, buttons);

        BorderPane borderProfile = new BorderPane(profile);
        borderProfile.setStyle("-fx-background-color: #C2DFff;");

        return new Scene(borderProfile, 700, 500);
    }
}
