package dataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private static Properties properties = new Properties();
    static {
        try {
            FileInputStream file = new FileInputStream("uiConfig.properties");

            properties.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }public static  String get(String keyword){

        return properties.getProperty(keyword);
    }

}
