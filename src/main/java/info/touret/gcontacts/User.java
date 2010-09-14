/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.touret.gcontacts;

/**
 *
 * @author touret-a
 */
public class User {

    private String login;
    private String passwd;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public User(String login, String passwd) {
        this.login = login;
        this.passwd = passwd;
    }

    public String toString() {
        return "User{" + "login=" + login + "passwd=xxxxxxx}";
    }
}
