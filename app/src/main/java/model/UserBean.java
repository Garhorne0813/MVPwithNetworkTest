package model;

public class UserBean implements UserModel{

    String account;
    String password;

    public UserBean(String account,String password) {
        this.account = account;
        this.password = password;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
