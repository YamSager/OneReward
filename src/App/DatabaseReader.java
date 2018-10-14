package App;

import Model.Admin;
import Model.Customer;
import Model.User;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Sam Yager on 6/19/2018.
 */
public class DatabaseReader {
    private static Connection conn;
    private File path = new File("./data/OneRewardDB.db");

    private static ArrayList<User> getUsers(ResultSet resultSet){
        ArrayList<User> users = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user;
                String UID = resultSet.getString("UID");
                String first = resultSet.getString("firstName");
                String last = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String admin = resultSet.getString("admin");
                String emailList = resultSet.getString("emailList");
                int nextReward = resultSet.getInt("nextReward");
                boolean adminRights;
                boolean emailListRights;
                if (admin.equals("false")) {
                    adminRights = false;
                } else {
                    adminRights = true;
                }
                if (emailList.equals("false")) {
                    emailListRights = false;
                } else {
                    emailListRights = true;
                }
                if (adminRights) {
                    user = new Admin(UID, first, last, email, emailListRights, nextReward);
                } else {
                    user = new Customer(UID, first, last, email, emailListRights, nextReward);
                }
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public boolean setConn(){
        conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:" + path.getAbsolutePath());
            if (conn != null){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static ArrayList<User> search(String search){
        try {
            String query = "";
            String strings[] = search.split(" ");
            int i = 0;
            for(String string: strings){
                query += " firstName LIKE '" + string + "' OR lastName LIKE '" + string + "'";
                if(i < strings.length - 1){
                    query += " OR";
                }
                i++;
            }
            Statement statement = conn.createStatement();
            String finalQuery = "SELECT * FROM Customers WHERE" + query;
            ResultSet resultSet = statement.executeQuery(finalQuery);
            return getUsers(resultSet);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ArrayList<User> search(){
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Customers";
            ResultSet resultSet = statement.executeQuery(query);
            return getUsers(resultSet);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public User getUserFromUID(String UID){
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Customers WHERE UID = '" + UID + "'";
            ResultSet resultSet = statement.executeQuery(query);
            return getUsers(resultSet).get(0);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void storeUser(User user){
        try{
            Statement statement = conn.createStatement();
            String query = "INSERT INTO Customers (UID, firstName, lastName, email, emailList, nextReward, admin) VALUES" + user.toString();
            statement.executeQuery(query);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateUser(User user){
        try{
            Statement statement = conn.createStatement();
            String query = "UPDATE Customers SET nextReward = " + user.getPoints() + " WHERE UID = '" + user.getUID() + "'";
            statement.executeQuery(query);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setPath(String path){

    }
}
