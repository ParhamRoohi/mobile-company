import java.io.IOException;

public class UNException extends Exception{
    String username;
   public UNException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String toString() {
        return "Your plan username didn't match our pattern, a new username " + username
                + " has been assigned to the plan.";
    } 
}
