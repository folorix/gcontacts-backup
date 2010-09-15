package info.touret.gcontacts;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 * Main class
 * @author $Author$
 * @version $Revision$  / $Name$
 */
public class App {

    private static Options options;

    public static void setOptions(Options options) {
        App.options = options;
    }

    /**
     *
     * @return The command line options
     */
    private static Options getOptions() {
        if (options == null) {
            Options _options = new Options();
            _options.addOption("ph", true, "Proxy Host");
            _options.addOption("pp", true, "Proxy Port");
            _options.addOption("u", true, "User Login (REQUIRED)");
            _options.addOption("p", true, "User Password (REQUIRED)");
            _options.addOption("d", true, "Directory Output (REQUIRED)");
            _options.addOption("h", false, "Help message");
            setOptions(_options);
        }
        return options;
    }

    /**
     * Initialize http proxy settings
     * @param host the proxy host
     * @param port the proxy port
     */
    private static void initProxy(String host, String port) {
        System.getProperties().put("proxySet", "true");
        System.setProperty("http.proxyHost", host);
        System.setProperty("http.proxyPort", port);
        System.setProperty("https.proxyHost", host);
        System.setProperty("https.proxyPort", port);
    }
    private static final Logger trace = Logger.getLogger(App.class.getCanonicalName());

    private static void showHelp() {
        HelpFormatter helpformatter = new HelpFormatter();
        helpformatter.printHelp("run", getOptions());
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            CommandLineParser parser = new PosixParser();
            CommandLine cmd = parser.parse(getOptions(), args);
            if (cmd.hasOption("h")) {
                showHelp();
            } else {
                if (cmd.hasOption("ph") && cmd.hasOption("pp")) {
                    trace.info("Initializing proxy configuration ...");
                    initProxy(cmd.getOptionValue("ph"), cmd.getOptionValue("pp"));
                }
                if(!cmd.hasOption("u")||!cmd.hasOption("p")||!cmd.hasOption("d")){
                    throw new ParseException("These Arguments Are required : user,password et destination");
                }
                ContactManager manager = new ContactManager();
                manager.backup(new User(cmd.getOptionValue("u"), cmd.getOptionValue("p")), cmd.getOptionValue("d"));
            }
        } catch (GContactException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, "Parsing options failed! Reason :", ex);
            showHelp();
        }
    }
}
