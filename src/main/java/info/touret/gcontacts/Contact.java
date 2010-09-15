/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package info.touret.gcontacts;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;

/**
 * Used to store contacts datas. Now, only phone numbers and emails
 * @author $Author$
 * @version $Revision$  / $Name$
 */
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class Contact {
    private List<String> emails;
    private List<String> phoneNumbers;
    private String fullname;

    /**
     * 
     * @return a list of mails
     */
    public List<String> getEmails() {
        return emails;
    }

    /**
     *
     * @param emails
     */
    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    /**
     *
     * @return
     */
    public String getFullname() {
        return fullname;
    }

    /**
     *
     * @param fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     *
     * @return
     */
    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     *
     * @param phoneNumbers
     */
    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    /**
     *
     * @param emails
     * @param phoneNumbers
     * @param fullname
     */
    public Contact(List<String> emails, List<String> phoneNumbers, String fullname) {
        this.emails = emails;
        this.phoneNumbers = phoneNumbers;
        this.fullname = fullname;
    }

    /**
     *
     */
    public Contact() {
    }

    
}
