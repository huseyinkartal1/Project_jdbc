package jdbc2;

import java.sql.*;
import java.util.Scanner;

public class jdbc4_insertDeleteUpdate {


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

        rs.last();

        rsmd=rs.getMetaData();
        int a=rsmd.getColumnCount();
        while(rs.next()){
            for (int i = 0; i <a ; i++) {
                System.out.print("%-20s");
            }

        }

        System.out.println(rs.last());




        close();

    }

}
