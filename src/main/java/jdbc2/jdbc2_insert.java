package jdbc2;

import org.testng.annotations.*;

import java.sql.*;
import java.util.Scanner;

public class jdbc2_insert {


    static Connection conn;
    static Statement stat;
    ResultSet rs;
    ResultSetMetaData rsmd;
    static Scanner scan;


    @BeforeTest
    public static void beforeTest() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://142.93.110.12/tempdb",
                "gsuser",
                "Gsuser!123456");
        stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    }

    @AfterTest
    public void afterTest() throws SQLException {
        conn.close();
        stat.close();
    }

    @Test
    public void test1() throws SQLException {

        String sql = "INSERT INTO kartal VALUES('Veli','Kalil','12','IST')";
        int insNum = stat.executeUpdate(sql);
        System.out.println(insNum+" kayit eklendi");





    }

    @Test
    public void test2() throws SQLException {


        String sql = "SELECT * FROM kartal";
        rs = stat.executeQuery(sql);
        ResultSetMetaData rsmd=rs.getMetaData();
        int cols= rsmd.getColumnCount();

        for (int i = 1; i <=cols ; i++) {
            System.out.println(rsmd.getColumnName(i));
        }

        while (rs.next()) {
            for (int i = 0; i <=cols ; i++) {
                System.out.printf("%-20s",rs.getString(i));
            }
        }
    }

    public static void main(String[] args) throws SQLException {

        connect();

        scan=new Scanner(System.in);

        System.out.println("İsim giriniz: ");
        String isim=scan.nextLine();

        System.out.println("Soyisim giriniz: ");
        String soyisim=scan.nextLine();


        System.out.println("Yas giriniz: ");
        int yas=scan.nextInt();

        scan.nextLine();

        System.out.println("Sehir giriniz: ");
        String sehir=scan.nextLine();

        String sql = "INSERT INTO kartal VALUES('"+isim+"','"+soyisim+"','"+yas+"','"+sehir+"')";
        int insNum = stat.executeUpdate(sql);
        System.out.println(insNum+" kayit eklendi");

    }

        public static void connect() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://142.93.110.12/tempdb",
                "gsuser",
                "Gsuser!123456");
        stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    }


}
