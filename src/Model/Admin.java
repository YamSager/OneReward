package Model;

/**
 * Created by Sam Yager on 6/19/2018.
 */
public class Admin extends User {
    public Admin(String UID, String first, String last, String email, Boolean emailList, int nextReward){
        super(UID, first, last, email,emailList, nextReward, true);
    }
}
