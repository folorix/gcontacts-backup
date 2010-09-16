/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.touret.gcontacts;

import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.FullName;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.data.extensions.PhoneNumber;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrap the contact entities returned by google to a custom class
 * @author $Author$
 * @version $Revision$  / $Name$
 */
public class ContactWrapper {

    private ContactEntry entry;
    private Contact contact;

    /**
     *
     * @param entry
     */
    public ContactWrapper(ContactEntry entry) {
        this.entry = entry;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     *Transform the contact to a google entry
     * @return the entry
     */
    public ContactEntry getEntry() {
        if (this.contact != null) {
            entry = new ContactEntry();
            Name name = new Name();
            final String NO_YOMI = null;
            name.setFullName(new FullName(contact.getFullname(), NO_YOMI));
            entry.setName(name);
            int i = 0;
            if (contact.getPhoneNumbers() != null) {
                List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>(contact.getPhoneNumbers().size());
                for (String current : contact.getPhoneNumbers()) {
                    PhoneNumber number = new PhoneNumber();
                    number.setPhoneNumber(current);
                    number.setLabel("phoneNumber-" + (i++));
                    entry.addPhoneNumber(number);

                }
            }

            if (contact.getEmails() != null) {
                List<Email> emails = new ArrayList<Email>(contact.getEmails().size());
                int j = 0;
                for (String current : contact.getEmails()) {
                    Email mail = new Email();
                    mail.setAddress(current);
                    mail.setLabel("email-" + (j++));
                    entry.addEmailAddress(mail);
                }
            }
        }
        return entry;
    }

    /**
     *
     * @param _entry
     */
    public void setEntry(ContactEntry _entry) {
        this.entry = _entry;
    }

    /**
     * Transform the contact entry. If the full name is blank, the string '(Without Name)' is initialized
     * @return the contact pojo regarding to the entry initialized by the method setEntry or the constructor
     * @see #setEntry(com.google.gdata.data.contacts.ContactEntry) 
     */
    public Contact getContact() {
        if (this.entry != null) {// on est dans la transformation entry -> contact
            contact = new Contact();
            List<String> emails = new ArrayList<String>(getEntry().getEmailAddresses().size());
            // extract emails
            for (Email currentEmail : getEntry().getEmailAddresses()) {
                emails.add(currentEmail.getAddress());
            }
            // extract phone numbers
            List<String> phoneNumbers = new ArrayList<String>(getEntry().getPhoneNumbers().size());
            for (PhoneNumber currentPhoneNumber : getEntry().getPhoneNumbers()) {
                phoneNumbers.add(currentPhoneNumber.getPhoneNumber());
            }
            contact.setEmails(emails);
            contact.setPhoneNumbers(phoneNumbers);
            // extract full name
            if (getEntry().getName() == null) {
                contact.setFullname("(Without Name)");
            } else if (getEntry().getName().hasFullName()) {
                contact.setFullname(getEntry().getName().getFullName().getValue());
            } else {
                contact.setFullname(getEntry().getName().toString());
            }
        }
        return contact;
    }

    /**
     * Constructor
     */
    public ContactWrapper() {
    }
}
