package selenium.util;

import java.io.*;
import java.util.Properties;

public class PropertiesUtil {
    private static Properties prop = null;
    private static Properties writeProp = null;
    InputStream input = null;
    OutputStream output = null;

    public PropertiesUtil (String propFilePath) {
        try {
            input = new FileInputStream(propFilePath);
            prop = new Properties();
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void initWriteProperties(String writePropFp){
        try{
            output = new FileOutputStream(writePropFp);
            writeProp = new Properties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeChangesProperties() {
        try {
            writeProp.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPropValue(String key) {
       return prop.getProperty(key);
    }

    public static void setPropValue(String key, String value){
        writeProp.setProperty(key, value);
    }

}
