package jdbc3;

import java.sql.*;

public class SQLite2 {

    public static void main(String[] args) throws SQLException {

        Connection conn;
        Statement stat;
        ResultSet rs;



        conn=DriverManager.getConnection("jdbc:sqlite:src/data.sqlite");

        stat=conn.createStatement();
        stat.setQueryTimeout(10);

        String sql= "CREATE TABLE kartal(adi text, soyadi text, yas int, sehir text)";



        stat.executeUpdate(sql);



        System.out.println();







    }


}
