/**
 * User class represents an user in the library system
 * It contains the ID, username, password, email information and godmode status which represents whether user is an admin or not
 */
public class User {
    private int ID;
    private String username;
    private String password;
    private String email;
    private int godmode;

    //Constructor
    public User(int ID,String username, String password, String email, int godmode) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.godmode = godmode;
    }

    public void addBook () {

    }

    //Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getID() {
        return ID;
    }

    public int getGodmode () {
        return godmode;
    }

    //Setters
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setID(int newID) {
        this.ID = newID;
    }

    public void setGodmode (int newGodmode) {
        this.godmode= newGodmode;
    }
}