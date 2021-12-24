package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class ReadProperties {
    private static final Logger logger = LoggerFactory.getLogger(ReadProperties.class);
    public static String defaultPropertiesFile = "testData/config.properties";
    public static Properties confProperties = new Properties();

    public static void loadPropFromFile(String fileName) {
        try {
            InputStream inputStream = ReadProperties.class.getClassLoader().getResourceAsStream(fileName);
            new FileReader(fileName);
            InputStream inputStream1 = new FileInputStream(fileName);
            confProperties.load(inputStream1);
        } catch (IOException var4) {
            var4.printStackTrace();
            logger.error("Failed to load prop files -- " + var4);
        }
    }

    public static String readConfigProperty(String key) {
        String value = null;
        try {
            loadPropFromFile(defaultPropertiesFile);
            value = confProperties.getProperty(key);
        } catch (Exception var3) {
            var3.printStackTrace();
            logger.error("Unable to find property - " + key + " check the properties file---" + var3);
        }
        return value;
    }
}
