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



        String sql="SELECT * FROM doctors";

        rs = statement.executeQuery(sql);

        ResultSetMetaData rsmd = rs.getMetaData();

        int col=rsmd.getColumnCount();

        while (rs.next()){
            for (int i = 1; i <= col; i++) {
                System.out.println(rs.getString(i));
            }


        }
    }

    @BeforeTest
    public void beforeClass() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306",
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
