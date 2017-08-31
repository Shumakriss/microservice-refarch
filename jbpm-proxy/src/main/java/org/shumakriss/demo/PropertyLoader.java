package org.shumakriss.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Chris on 9/7/16.
 */
public class PropertyLoader {

    public static String getHostname(){
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = PropertyLoader.class.getClassLoader().getResourceAsStream("application.properties");
            prop.load(input);
            return prop.getProperty("hostname");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}
