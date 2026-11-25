package qlnh;

import java.sql.Connection;
import java.sql.DriverManager;

public class QLNH {

    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=QuanLySinhVien;encrypt=false;trustServerCertificate=true",
                "sa",
                "123456"
            );

            System.out.println("Kết nối thành công!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}