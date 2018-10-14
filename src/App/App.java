package App;

import UI.UserInterface;
import javafx.stage.Stage;

/**
 * Created by Sam Yager on 6/20/2018.
 */
public class App {
    private static DatabaseReader dbReader;
    private static SerialReader serialReader;
    private static Stage primaryStage;

    public static void runApp(){
        dbReader = new DatabaseReader();
        serialReader = new SerialReader();
        if(serialReader.getPort() && dbReader.setConn()){
            serialReader.readPort(dbReader);
        }
    }
}
