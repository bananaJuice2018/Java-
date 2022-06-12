import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class AppLog {
    static Logger LOGGER = Logger.getLogger(AppLog.class.getName());
    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");

        LOGGER.info("This is an information message.");
        LOGGER.debug("This is a debug message.");
        LOGGER.warn("This is a warning message");
        LOGGER.error("This is an error message.");
        LOGGER.fatal("This is a fatal message");
    }
}
