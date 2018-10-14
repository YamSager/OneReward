package UI;

import App.DatabaseReader;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by Sam Yager on 6/20/2018.
 */
public class UserInterface extends Application {
    private static Stage mainStage;

    public static Stage getStage(){
        return mainStage;
    }

    public static void main(String args[]){
        App.App.runApp();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        mainStage = primaryStage;

        primaryStage.setOnCloseRequest(e -> {
            primaryStage.close();
            System.exit(0);
        });

        File path = new File("assets/Logo.png");
        Image image = new Image(path.toURI().toString(), 24, 24, false, true);

        primaryStage.setScene(HomePage.buildHomePage(primaryStage));
        primaryStage.setTitle("OneReward");
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }
}
