/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.touret.gcontacts;

import com.google.gdata.client.Query;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Manager of extraction and serialization of the contacts
 * @author $Author$
 * @version $Revision$  / $Name$
 */
public class ContactManager {

    private static final String FEED_URL = "http://www.google.com/m8/feeds/contacts/default/full";
    private static Logger trace = Logger.getLogger(ContactManager.class.getCanonicalName());

    private ContactsService logon(User user) throws AuthenticationException {
        ContactsService service = new ContactsService(ContactsService.CONTACTS_SERVICE);
        trace.info("Logon using user :" + user);
        service.setUserCredentials(user.getLogin(), user.getPasswd());
        trace.info("User " + user + " was successfully authentified !");
        return service;
    }

    private List<Contact> getContacts(User user) throws GContactException {
        List<Contact> contacts = new ArrayList<Contact>();
        try {
            ContactsService service = logon(user);
            URL feedUrl = new URL(FEED_URL);
            Query query = new Query(feedUrl);
            //todo a mettre en parametre du main
            query.setMaxResults(1000);
            ContactFeed resultFeed = service.query(query, ContactFeed.class);
            ContactWrapper wrapper = new ContactWrapper();
            int i = 0;
            for (ContactEntry current : resultFeed.getEntries()) {
                trace.info("Retreiving contact #" + (i++) + "...");
                wrapper.setEntry(current);
                contacts.add(wrapper.getContact());
            }
        } catch (AuthenticationException e) {
            throw new GContactException(e);
        } catch (ServiceException e1) {
            throw new GContactException(e1);
        } catch (IOException e2) {
            throw new GContactException(e2);
        }
        return contacts;
    }

    /**
     * Extract and store all the contacts of the user on the file specified on parameters
     * @param user
     * @param output
     * @throws GContactException
     */
    public void backup(User user, String output) throws GContactException {
        try {
            trace.info("Starting backup of user :" + user + " into directory " + output);
            List<Contact> contacts = getContacts(user);
            GContactRoot root = new GContactRoot();
            root.setContacts(contacts);
            JAXBContext context = JAXBContext.newInstance(GContactRoot.class);
            Marshaller m = context.createMarshaller();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String datetime = format.format(new Date());
            String filename = new StringBuffer(output).append(File.separator).append(user.getLogin()).append("-").append(datetime).append(".xml").toString();
            m.marshal(root, new FileOutputStream(filename));
            trace.info("Backup done");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new GContactException(ex);
        } catch (JAXBException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new GContactException(ex);
        }

    }

    /**
     * Restore all the entries founded in the file specified by the input parameter in the calendar of the user
     * @param user
     * @param input the absolute path
     * @throws GContactException
     */
    public void restore(User user, String input) throws GContactException {
        trace.info("Starting restore of user :" + user + " from file" + input);
        try {
            trace.info("Extracting contacts from file : " + input + " ...");
            JAXBContext context = JAXBContext.newInstance(GContactRoot.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            GContactRoot contactsRoot = (GContactRoot) unmarshaller.unmarshal(new File(input));
            trace.info("Extracting contacts from file : " + input + " done.");

            ContactsService service = logon(user);
            ContactWrapper wrapper = new ContactWrapper();
            if (contactsRoot != null && contactsRoot.getContacts() != null) {
                for (Contact current : contactsRoot.getContacts()) {
                    // Ask the service to insert the new entry
                    URL postUrl = new URL(FEED_URL);
                    wrapper.setContact(current);
                    trace.info("Inserting user : " + current.toString());
                    service.insert(postUrl, wrapper.getEntry());
                }
            }
            trace.info("Insertion done :)");
        } catch (IOException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new GContactException(ex);
        } catch (ServiceException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, "Erreur lors de l' insertion des donnees dans Google Contacts", ex);
            throw new GContactException(ex);
        } catch (JAXBException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, "Erreur lors de la recuperation des donnees provenant du fichier XML " + input, ex);
            throw new GContactException(ex);
        }
    }
}
