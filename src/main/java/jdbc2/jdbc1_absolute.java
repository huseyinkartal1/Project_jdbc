package jdbc2;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class jdbc1_absolute {

    Connection conn;
    Statement stat;
    ResultSet rs;
    ResultSetMetaData rsmd;

    @BeforeTest
    public void beforeTest() throws SQLException {
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
    public void getResultSet() throws SQLException {
        String sql = "SELECT * FROM meslekler1";
        rs = stat.executeQuery(sql);
    }

    @Test(description = "rs.absolute", dependsOnMethods = "getResultSet")
    public void test1() throws SQLException {

        rs.next();
        System.out.println(
                rs.getString(1) + " " +
                        rs.getString(2) + " " +
                        rs.getString(3));

        rs.absolute(10);

        System.out.println(
                rs.getString(1) + " " +
                        rs.getString(2) + " " +
                        rs.getString(3));

        rs.previous();
        System.out.println(
                rs.getString(1) + " " +
                        rs.getString(2) + " " +
                        rs.getString(3));

    }

    @Test(description = "rs.previous", dependsOnMethods = "getResultSet")
    public void test2() throws SQLException {

        rs.next();
        rs.previous();
        System.out.println(
                rs.getString(1) + " " +
                        rs.getString(2) + " " +
                        rs.getString(3));
    }

    @Test(description = "rs.relative", dependsOnMethods = "getResultSet")
    public void test3() throws SQLException {
        rs.absolute(10);
        rs.relative(-2);
        System.out.println(
                rs.getString(1) + " " +
                        rs.getString(2) + " " +
                        rs.getString(3));
    }

    @Test(description = "rs.first-rs.last", dependsOnMethods = "getResultSet")
    public void test4() throws SQLException {
        rs.first();
        System.out.println(
                rs.getString(1) + " " +
                rs.getString(2) + " " +
                        rs.getString(3));
        rs.last();
        System.out.println(
                rs.getString(1) + " " +
                rs.getString(2) + " " +
                rs.getString(3));

    }

}
