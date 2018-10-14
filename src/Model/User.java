package Model;

/**
 * Created by Sam Yager on 6/19/2018.
 */
public abstract class User {
    private String UID;
    private String firstName;
    private String lastName;
    private String email;
    private boolean emailList;
    private boolean admin;
    private int points;
    private String name;

    public User(String UID, String first, String last, String email,
                Boolean emailList, int nextReward, boolean admin){
        this.UID = UID;
        firstName = first;
        lastName = last;
        this.email = email;
        this.emailList = emailList;
        this.points = nextReward;
        this.admin = admin;
        name = first + " " + last;
    }

    public void addRewards(int rewardPoints){
        points += rewardPoints;
    }

    public void redeemPoints(int rewardPoints){
        points -= rewardPoints;
    }

    public String getName(){
        return name;
    }

    public boolean isAdmin(){
        return admin;
    }

    public String getEmail(){
        return (email == null) ? "" : email;
    }

    public int getPoints(){
        return points;
    }

    public String getUID(){
        return UID;
    }

    public String toString(){
        return "('" + UID + "', '" + firstName + "', '" + lastName + "', '" + email + "', '" + emailList + "', " + points + ", '" + admin + "')";
    }
}
