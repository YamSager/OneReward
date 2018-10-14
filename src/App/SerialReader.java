package App;

import Model.User;
import UI.Confirmation;
import UI.NewUser;
import UI.Profile;
import UI.UserInterface;
import com.fazecast.jSerialComm.*;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SerialReader {
    public static SerialPort serialPort;

    public boolean getPort(){
        SerialPort ports[] = SerialPort.getCommPorts();
        if(ports.length == 0) {
            System.out.println("No device detected");
            System.exit(0);
            return false;
        } else if(ports.length == 1){
            System.out.println("Device found");
            serialPort = SerialPort.getCommPort(ports[0].getSystemPortName());
        } else {
            Scanner input = new Scanner(System.in);
            int i = 1;
            System.out.println("Available devices");
            for(SerialPort port: ports){
                System.out.println(i++ + ": " + port.getSystemPortName());
            }
            System.out.println("Please select COM PORT: 'COM#'");
            serialPort = SerialPort.getCommPort(input.nextLine());
        }
        serialPort.openPort();
        if(serialPort.isOpen()){
            System.out.println("Successfully connected");
            return true;
        } else {
            System.out.println("Could not make connection");
            System.exit(0);
            return false;
        }
    }

    public void readPort(DatabaseReader dbReader){
        serialPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                try {
                    TimeUnit.MICROSECONDS.sleep(11500);
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
                byte[] newData = new byte[serialPort.bytesAvailable()];
                int numRead = serialPort.readBytes(newData, newData.length);
                String out = "";
                for (int i = 0; i < numRead;i++) {
                    out += (char)newData[i];
                }
                out = out.trim();
                //System.out.println(out);
                if(out.matches("([A-F0-9][A-F0-9] [A-F0-9][A-F0-9] [A-F0-9][A-F0-9] [A-F0-9][A-F0-9])")) {
                    User user = dbReader.getUserFromUID(out);
                    if (user == null) {
                        final String UID = out;
                        Platform.runLater(() ->
                                UserInterface.getStage().setScene(NewUser.buildNewUser(UID, UserInterface.getStage())));
                    } else {
                        System.out.println(user.toString());
                        if(user.isAdmin()){
                            Platform.runLater(() ->
                                    Confirmation.confirm("Admin Acount\nWould you like to view your Profle or the Customer List?", UserInterface.getStage(), user));
                        } else {
                            Platform.runLater(() ->
                                    UserInterface.getStage().setScene(Profile.buildProfile(user, UserInterface.getStage())));
                        }
                    }
                }
            }
        });
    }
}

