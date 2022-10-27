package jdbc2;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.Scanner;

public class jdbc3_prepareStatement {


    static Connection conn;
    static Statement stat;
    static ResultSet rs;
    static ResultSetMetaData rsmd;
    static Scanner scan;
    static PreparedStatement pstmt;


    public static void connect() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://142.93.110.12/tempdb",
                "gsuser",
                "Gsuser!123456");
        pstmt = conn.prepareStatement("INSERT INTO kartal VALUES(?, ?, ?, ?);");
        stat=conn.createStatement();
        rs=stat.executeQuery("SELECT * FROM kartal");
    }

    public static void close() throws SQLException {
        conn.close();
        pstmt.close();
    }


    public static void main(String[] args) throws SQLException {

        connect();

        pstmt.setString(1, "prepare");
        pstmt.setString(2, "Statement");
        pstmt.setInt(3, 3306);
        pstmt.setString(4, "IntelliJ");

        pstmt.executeUpdate();






        close();

    }

}
