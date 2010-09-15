/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.touret.gcontacts;

/**
 * Google User
 * @author $Author$
 * @version $Revision$  / $Name$
 */
public class User {

    private String login;
    private String passwd;

    /**
     *
     */
    public User() {
    }

    /**
     *
     * @return the mail of the user (ex.: john.doe@gmail.com)
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @return the password of the user
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     *
     * @param passwd
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     *
     * @param login
     * @param passwd
     */
    public User(String login, String passwd) {
        this.login = login;
        this.passwd = passwd;
    }

    public String toString() {
        return "User{" + "login=" + login + "passwd=xxxxxxx}";
    }
}
