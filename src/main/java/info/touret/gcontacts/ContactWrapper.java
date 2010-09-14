/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.touret.gcontacts;

import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.PhoneNumber;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author touret-a
 */
public class ContactWrapper {

    private ContactEntry entry;

    public ContactWrapper(ContactEntry entry) {
        this.entry = entry;
    }

    public ContactEntry getEntry() {
        return entry;
    }

    public void setEntry(ContactEntry _entry) {
        this.entry = _entry;
    }

    public Contact getContact() {
        Contact contact = new Contact();
        List<String> emails = new ArrayList<String>(getEntry().getEmailAddresses().size());
        for (Email currentEmail : getEntry().getEmailAddresses()) {
            emails.add(currentEmail.getAddress());
        }
        List<String> phoneNumbers = new ArrayList<String>(getEntry().getPhoneNumbers().size());
        for (PhoneNumber currentPhoneNumber : getEntry().getPhoneNumbers()) {
            phoneNumbers.add(currentPhoneNumber.getPhoneNumber());
        }
        contact.setEmails(emails);
        contact.setPhoneNumbers(phoneNumbers);
        if(getEntry().getName()==null){
            contact.setFullname("(Without Name)");
        }else
        if (getEntry().getName().hasFullName()) {
            contact.setFullname(getEntry().getName().getFullName().getValue());
        } else {
            contact.setFullname(getEntry().getName().toString());
        }

        return contact;
    }

    public ContactWrapper() {
    }

}
