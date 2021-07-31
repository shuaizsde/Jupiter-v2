package com.laioffer.jupiter.db;

import com.laioffer.jupiter.ultils.PropertyReader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MySQLDBUtil {
    //就是把各种db相关的登录信息组合起来变成一个url
    public static String getMySQLAddress() throws IOException {//如果config里没有对应的property  扔出exception
        PropertyReader reader = new PropertyReader();
        String port = reader.getProperty("db_port");
        String dbName = reader.getProperty("db_name");
        String instance = reader.getProperty("db_instance");
        String username = reader.getProperty("user");
        String password = reader.getProperty("password");

        // Encode special characters in your password.
        try {
            password = URLEncoder.encode(password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //username 和password 是从config.properties 读出来的
        return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s&autoReconnect=true&serverTimezone=UTC&createDatabaseIfNotExist=true",
                instance, port, dbName, username, password);
    }

}
