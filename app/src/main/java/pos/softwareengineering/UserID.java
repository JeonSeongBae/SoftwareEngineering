package pos.softwareengineering;

public class UserID {
    String ID = " ";
    String password = " ";

    public UserID(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPassword(String password) {
        password = password;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }
}
