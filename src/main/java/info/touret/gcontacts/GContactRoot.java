/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package info.touret.gcontacts;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author touret-a
 */

@XmlRootElement
public class GContactRoot {
private List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public GContactRoot() {
    }
}
