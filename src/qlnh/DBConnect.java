package qlnh;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

    public static Connection getConnection() {

        try {

            String url =
                "jdbc:sqlserver://localhost:1433;"
                + "databaseName=QLNH;"
                + "encrypt=false;"
                + "trustServerCertificate=true";

            String user = "sa";
            String password = "123456";

            return DriverManager.getConnection(
                url,
                user,
                password
            );

        } catch(Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}