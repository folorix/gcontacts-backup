package info.touret.gcontacts;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 * Hello world!
 *
 */
public class App {

    private static Options getOptions() {
        Options options = new Options();
        options.addOption("ph", true, "Proxy Host");
        options.addOption("pp", true, "Proxy Port");
        options.addOption("u", true, "User Login");
        options.addOption("p", true, "User Password");
        options.addOption("d", true, "Directory Output");
        return options;
    }

    private static void initProxy(String host, String port) {
        System.getProperties().put("proxySet", "true");
        System.setProperty("http.proxyHost", host);
        System.setProperty("http.proxyPort", port);
        System.setProperty("https.proxyHost", host);
        System.setProperty("https.proxyPort", port);
    }

    public static void main(String[] args) {
        try {
            CommandLineParser parser = new PosixParser();
            CommandLine cmd = parser.parse(getOptions(), args);
            if (cmd.hasOption("ph") && cmd.hasOption("pp")) {
                initProxy(cmd.getOptionValue("pp"), cmd.getOptionValue("ph"));
            }
            ContactManager manager = new ContactManager();
            manager.backup(new User("alexandre.touret@gmail.com", "figa,#rO!"), "d:/test");
        } catch (GContactException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
