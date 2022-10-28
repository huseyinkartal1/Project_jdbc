package utilities;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws SQLException {


        ConnectionManager getConnection=new ConnectionManager("jdbc:sqlite:src/data.sqlite");

        ResultSet rs= getConnection.getResultSet("SELECT * FROM kartal");


        ResultSetMetaData rsmd=rs.getMetaData();

        while (rs.next()){
            for (int i = 1; i < rs.getMetaData().getColumnCount() ; i++) {
                System.out.printf("%-5s %-5s %-5s %-5s\n",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }

        }

        getConnection.close();




        ConnectionManager mySqlSakila= new ConnectionManager("jdbc:mysql://142.93.110.12:3306/sakila",
        "gsuser",
        "Gsuser!123456");

        ResultSet rs1= mySqlSakila.getResultSet("SELECT title, COUNT(*) FROM film " +
                "LEFT JOIN film_actor ON film.film_id=film_actor.film_id " +
                "GROUP BY title;");

        while (rs1.next()){
            for (int i = 0; i < 2 ; i++) {
                System.out.printf("%-5s %-5s\n",
                        rs1.getString(1),
                        rs1.getString(2));
            }
        }

        mySqlSakila.close();

    }
}
