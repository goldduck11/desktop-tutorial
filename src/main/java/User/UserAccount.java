package User;

public class UserAccount {

    private String userName;
    private String password;
    private String Quastion;


    public UserAccount() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuastion() {
        return Quastion;
    }

    public void setQuastion(String quastion) {
        Quastion = quastion;
    }
}