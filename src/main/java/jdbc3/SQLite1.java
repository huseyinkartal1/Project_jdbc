package jdbc3;

import java.sql.*;

public class SQLite1 {


    public static void main(String[] args) throws SQLException {
        Connection conn;
        Statement stat;
        ResultSet rs;


        conn=DriverManager.getConnection("jdbc:sqlite:src/data.sqlite");

        stat=conn.createStatement();
        stat.setQueryTimeout(30);

        rs=stat.executeQuery("SELECT make, COUNT(make) FROM cars " +
                        "LEFT JOIN personel ON cars.id=personel.car_id GROUP BY make");

        ResultSetMetaData rsmd=rs.getMetaData();



        int colCount=rs.getMetaData().getColumnCount();

        for (int i = 1; i <=colCount ; i++) {
            System.out.printf("%-5s ",rsmd.getColumnDisplaySize(i));
        }
        System.out.println();
        String format="";

        while (rs.next()){

            for (int i = 1; i <=colCount ; i++) {
                System.out.printf("%-20s",rs.getString(i));
            }
            System.out.println();
        }









        conn.close();
        stat.close();

    }










}
