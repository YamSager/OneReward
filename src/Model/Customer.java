package Model;

/**
 * Created by Sam Yager on 6/19/2018.
 */
public class Customer extends User {
    public Customer(String UID, String first, String last, String email, boolean emailList, int nextReward){
        super(UID, first, last, email,emailList, nextReward, false);
    }
}
