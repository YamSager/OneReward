package UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Sam Yager on 6/22/2018.
 */
public class AlertBox {

    public static void alertBox(String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setHeight(250);
        window.setWidth(300);

        Label label = new Label(message);
        label.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane(label);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
