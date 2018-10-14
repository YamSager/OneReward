package UI;

import App.DatabaseReader;
import Model.Admin;
import Model.Customer;
import Model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Sam Yager on 6/20/2018.
 */
public class NewUser {
    public static Scene buildNewUser(String UID, Stage primaryStage){

        Label nameF = new Label("First Name: ");
        Label nameL = new Label("Last Name: ");
        Label emailL = new Label("Email Address: ");
        Label pointsL = new Label("Starting Points: ");

        nameF.setPadding(new Insets(5, 21, 0,0));
        nameL.setPadding(new Insets(5, 0, 0,0));
        emailL.setPadding(new Insets(5, 3, 0,0));
        pointsL.setPadding(new Insets(5, 0, 0,0));

        TextField nameFirst = new TextField();
        TextField email = new TextField();
        TextField points = new TextField();
        TextField nameLast = new TextField();

        CheckBox emailList = new CheckBox("Email List");
        CheckBox admin = new CheckBox("Admin");

        emailList.setPadding(new Insets(0,0,8,0));
        //admin.setPadding(new Insets(5,0,5,10));

        Button cancel = new Button("Cancel");
        Button submit = new Button("Submit New User");

        cancel.setPadding(new Insets(20));
        submit.setPadding(new Insets(20));

        cancel.setOnAction(e -> {
            e.consume();
            Confirmation.confirmBox("Are you sure you want to cancel?", primaryStage);
        });

        submit.setOnAction(e -> {
            User user;
            int pointValue = 0;
            if(points.getText().matches("-?\\d+")){
                pointValue = Integer.parseInt(points.getText());
            }
            if(admin.isSelected()) {
                user = new Admin(UID, nameFirst.getText(), nameLast.getText(), email.getText(), emailList.isSelected(), pointValue);
            } else {
                user = new Customer(UID, nameFirst.getText(), nameLast.getText(), email.getText(), emailList.isSelected(), pointValue);
            }
            DatabaseReader.storeUser(user);
            primaryStage.setScene(HomePage.buildHomePage(primaryStage));
        });

        HBox cancelBox = new HBox();
        cancelBox.getChildren().addAll(cancel);
        cancelBox.setPadding(new Insets(0,0,0,84));

        HBox submitBox = new HBox();
        submitBox.getChildren().addAll(submit);
        submitBox.setPadding(new Insets(6,0,0,62));

        HBox name = new HBox();
        name.getChildren().addAll(nameF, nameFirst);

        HBox name2 = new HBox();
        name2.getChildren().addAll(nameL, nameLast);

        HBox emailBox = new HBox();
        emailBox.getChildren().addAll(emailL, email);

        HBox pointBox = new HBox();
        pointBox.getChildren().addAll(pointsL, points);

        VBox checks = new VBox();
        checks.getChildren().addAll(emailList, admin);
        checks.setPadding(new Insets(5,0,0,62));

        VBox left = new VBox();
        left.setAlignment(Pos.CENTER);
        //left.setPadding(new Insets(0, 20, 0 , 0));
        left.getChildren().addAll(name, emailBox, pointBox, cancelBox);

        VBox right = new VBox();
        right.setAlignment(Pos.CENTER);
        right.setPadding(new Insets(0, 0, 0 , 10));
        right.getChildren().addAll(name2, checks, submitBox);

        HBox content = new HBox();
        content.setAlignment(Pos.CENTER);
        content.getChildren().addAll(left, right);
        content.setPadding(new Insets(0, 50, 0 , 0));

        BorderPane pane = new BorderPane(content);
        pane.setStyle("-fx-background-color: #C2DFff;");

        return new Scene(pane, 700, 500);
    }

}
