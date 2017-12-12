import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoggerProperties
{
    /** Logger properties holder. */
    public static Properties prop;

    //load properties automatically.
    static{loadProperties();}

    /**
     * Load the properties of the Software app.
     * @throws IOException ex- File management exception.
     */
    public static void loadProperties()
    {

        prop = new Properties();
        InputStream input = null;

        try
        {
            String filename = "resources/config.properties";
            input = LoggerProperties.class.getClassLoader().getResourceAsStream(filename);
            // load a properties file



            if(input != null)prop.load(input);

            else throw new IOException("Cannot find properties file {0} in classpath.".replace("{0}", filename));

            System.out.println("Searching for logbook in the following path:" + prop.getProperty("path"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally { //close file on failure.
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}