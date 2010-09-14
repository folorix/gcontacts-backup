/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package info.touret.gcontacts;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;

/**
 *
 * @author touret-a
 */
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class Contact {
    private List<String> emails;
    private List<String> phoneNumbers;
    private String fullname;

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Contact(List<String> emails, List<String> phoneNumbers, String fullname) {
        this.emails = emails;
        this.phoneNumbers = phoneNumbers;
        this.fullname = fullname;
    }

    public Contact() {
    }

    
}
