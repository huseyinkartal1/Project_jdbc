package jdbc1;

import com.mysql.cj.protocol.Resultset;
import org.testng.annotations.*;

import java.sql.*;

public class Kartal {

    Connection conn;
    Statement statement;
    ResultSet rs;

    @Test
    public void test() throws SQLException {

        String sql="SELECT * FROM kartal";
        String sql2="DELETE FROM kartal WHERE Adı IS NULL";
        String sql3="DELETE FROM kartal WHERE Soyadı IS NULL";
        String sql4="SELECT * FROM kartal WHERE Sehir LIKE 'İ%'";
        String sql5="SELECT COUNT(*) FROM kartal WHERE yas = 20";

        //  statement.executeQuery(sql3);

        statement.execute(sql3);
        rs = statement.executeQuery(sql5);

        ResultSetMetaData rsmd = rs.getMetaData();



        int col=rsmd.getColumnCount();
        for (int i = 1; i <= col; i++) {
            System.out.printf("%-10s ",rsmd.getColumnName(i));
        }
        System.out.println();
        System.out.println("-".repeat(50));

        while (rs.next()){
            for (int i = 1; i <= col; i++) {

                System.out.printf("%-10s ", rs.getString(i));
            }
            System.out.println();
        }
    }

    @BeforeTest
    public void beforeClass() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema",
                "root",
                "rootpass123");
        statement=conn.createStatement();

    }

    @AfterTest
    public void afterClass() throws SQLException {
        statement.close();
        conn.close();
    }



}
