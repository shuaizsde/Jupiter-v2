package com.laioffer.jupiter.ultils;

import com.laioffer.jupiter.db.MySQLDBUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
//read properties from resource/config.properties
public class PropertyReader {
    public String getProperty(String input) throws IOException {
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            InputStream inputStream = MySQLDBUtil.class.getClassLoader().getResourceAsStream(propFileName);
            prop.load(inputStream);
            return prop.getProperty(input);
        }catch (IOException e){
            e.printStackTrace();
            throw new IOException("Failed to parse data from config.properties");
        }
    }
}
