package dataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
//    private static Properties properties;
//    private static String configFilePath = "src/test/resources/uiConfig.properties";
//
//
//
//    public static Properties initializeProperties(){
//         properties = new Properties();
//        try {
//            properties.load(new FileInputStream(configFilePath));
//        }catch (IOException e){
//            e.printStackTrace();
//            System.out.println("File is not found");
//        }
//
//        return getProperties();
//
//    }
//
//
//    public static Properties getProperties() {
//        return properties;
//    }

    private static Properties properties = new Properties();

     static {

        try {
            FileInputStream file = new FileInputStream("uiConfig.properties");
            properties.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static  String get(String key){
        return properties.getProperty(key);
    }



}
