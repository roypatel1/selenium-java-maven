package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties configProperties = new Properties();

    static {
        try {
            InputStream inputStream = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("TestConfig.properties");

            if (inputStream == null) {
                throw new RuntimeException("TestConfig.properties not found in resources");
            }

            configProperties.load(inputStream);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public static String get(String key) {
        return configProperties.getProperty(key);
    }

    public static String getUrl() {
        String url = System.getProperty("url", get("url"));
        if (url != null) {
            return url;
        } else {
            return configProperties.getProperty("url");
        }
    }

    public static String getBrowser() {
        String browser = System.getProperty("browser", get("browser"));
        if (browser != null) {
            return browser;
        } else {
            return configProperties.getProperty("browser");
        }
    }

    public static boolean getHeadless() {
        String headless = System.getProperty("headless", get("headless"));
        if (headless != null) {
            return Boolean.parseBoolean(headless);
        } else {
            return Boolean.parseBoolean(configProperties.getProperty("headless"));
        }
    }

    public static String getEnv() {
        String env = System.getProperty("env", get("env"));
        if (env != null) {
            return env;
        } else {
            return configProperties.getProperty("env");
        }
    }
}