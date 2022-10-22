package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    public static Properties prop;
    private static File propertiesFile = new File(System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "main" + File.separator + "resources" + File.separator + "config.properties");

    public static Properties loadProperties() {
        prop = new Properties();

        try (FileInputStream fis = new FileInputStream(propertiesFile)) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
