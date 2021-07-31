package com.laioffer.jupiter.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySQLTableCreator {
    // Run this as a Java application to reset the database.
    //其他的程序没有main方法， tomcat会提供程序入口， 而mysql里面需要main方法来连接数据库
    public static void main(String[] args) {
        try {
            //***这种写法 在hibernate里面就会被完全包装好 只需提供url就可以了*** 本项目使用最原始的jdbc连接数据库

            // Step 1 Connect to MySQL.
            System.out.println("Connecting to " + MySQLDBUtil.getMySQLAddress());
            // 反射 reflection 的作用:在程序运行当中 才能决定behavior
            //这句话就是要把Driver这个class 加载到 java virtual machine(JVM)
            //相当于 com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Driver manager 会在一堆Driver种挑一个合适的(based on your input url)
            Connection conn = DriverManager.getConnection(MySQLDBUtil.getMySQLAddress());//和某一个数据库建立连接

            if (conn == null) {
                return;
            }

            // Step 2 Drop tables in case they exist.
            Statement statement = conn.createStatement();
            String sql = "DROP TABLE IF EXISTS favorite_records";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS items";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(sql);

            // Step 3 Create new tables.
            //为什么 长度为255 呢？ 0号位存具体长度
            sql = "CREATE TABLE items ("
                    + "id VARCHAR(255) NOT NULL,"
                    + "title VARCHAR(255),"
                    + "url VARCHAR(255),"
                    + "thumbnail_url VARCHAR(255),"
                    + "broadcaster_name VARCHAR(255),"
                    + "game_id VARCHAR(255),"
                    + "type VARCHAR(255) NOT NULL,"
                    + "PRIMARY KEY (id)"
                    + ")";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE users ("
                    + "id VARCHAR(255) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "first_name VARCHAR(255),"
                    + "last_name VARCHAR(255),"
                    + "PRIMARY KEY (id)"
                    + ")";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE favorite_records ("
                    + "user_id VARCHAR(255) NOT NULL,"
                    + "item_id VARCHAR(255) NOT NULL,"
                    + "last_favor_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "PRIMARY KEY (user_id, item_id),"
                    + "FOREIGN KEY (user_id) REFERENCES users(id),"
                    + "FOREIGN KEY (item_id) REFERENCES items(id)"
                    + ")";
            statement.executeUpdate(sql);

            // Step 4: insert fake user
            sql = "INSERT INTO users VALUES('1111', '3229c1097c00d497a0fd282d586be050', 'John', 'Smith')";
            statement.executeUpdate(sql);
            //why we need close?
            conn.close();
            System.out.println("Import done successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

