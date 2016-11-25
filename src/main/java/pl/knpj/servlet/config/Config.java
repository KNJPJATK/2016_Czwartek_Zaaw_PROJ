package pl.knpj.servlet.config;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by rpi on 25.11.16.
 */
public class Config {

    public static final String APP_CONFIG_FILE = "/app.properties";

    public static Config instance = null;

    public static synchronized Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private final Properties properties;

    public Config() {
        properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(APP_CONFIG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    @Override
    public String toString() {
        return "Config{" +
                "properties=" + properties +
                '}';
    }
}
