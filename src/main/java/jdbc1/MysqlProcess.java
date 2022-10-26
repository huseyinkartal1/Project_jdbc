package jdbc1;

import org.testng.annotations.Test;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MysqlProcess extends MysqlProcessBase{

    // actörlerin isimleri ve rol aldiklari film sayilari
    @Test
    public void sql1() throws SQLException {
        String sql = "SELECT first_name, last_name, COUNT(*) AS sayi" +
                " FROM actor" +
                " INNER JOIN film_actor ON actor.actor_id = film_actor.actor_id" +
                " WHERE first_name LIKE 'A%'" +
                " GROUP BY first_name, last_name" +
                " ORDER BY sayi DESC, first_name ASC, last_name ASC";
        rs = statement.executeQuery(sql);
        while (rs.next())
            System.out.printf("%-20s%-20s%d\n", rs.getString(1), rs.getString(2), rs.getInt(3));
    }

    @Test(description = "cinsiyetlere göre yas ortalamalari")
    public void test2() throws SQLException {
        String sql = "SELECT gender, AVG(age) AS sayi" +
                " FROM personel" +
                " GROUP BY gender";
        rs = statement.executeQuery(sql);
        while (rs.next())
            System.out.printf("%-20s%.2f\n", rs.getString(1), rs.getDouble(2));
    }


    @Test(description = "Field headers")
    public void test3() throws SQLException {
        String sql = "SELECT gender, AVG(age) AS sayi" +
                " FROM personel" +
                " GROUP BY gender";
        rs = statement.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();

        System.out.printf("%-20s%s\n",rsmd.getColumnName(1), rsmd.getColumnName(2));
        while (rs.next())
            System.out.printf("%-20s%.2f\n", rs.getString(1), rs.getDouble(2));
    }


    // personel tablosunu SELECT * ile cagirin
    // consola field basliklari ile datalarini for(int i=1;...)  ile yazdirin

    @Test(description = "consola field basliklari ile datalarini for(int i=1;...)  ile yazdirin")
    public void test4() throws SQLException {
        String sql = "SELECT id, first_name, last_name, age, gender FROM personel LIMIT 5;";
        rs = statement.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int rowNum = rsmd.getColumnCount();

        for (int i = 1; i <= rowNum ; i++) {
            System.out.printf("%20s", rsmd.getColumnName(i));
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rowNum; i++) {
                System.out.printf("%20s", rs.getString(i));
            }
            System.out.println();
        }
        //  id    first_name               first_name
    }

    @Test(description = "consola field basliklari ile datalarini for(int i=1;...)  ile yazdirin")
    public void test5() throws SQLException {
        String sql = "SELECT * FROM personel LIMIT 20;";
        rs = statement.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int rowNum = rsmd.getColumnCount();

        String format = "";
        String[] headers = new String[rowNum];
        for (int i = 1; i <= rowNum ; i++) {
            int size = rsmd.getColumnDisplaySize(i)>30 ? 30 : rsmd.getColumnDisplaySize(i);
            size = Math.max(size, rsmd.getColumnName(i).length());
            format +="%-" + size + "s";
            headers[i-1] = rsmd.getColumnName(i);
            System.out.println(rsmd.getColumnDisplaySize(i));
        }
        format +="\n";
        System.out.printf(format, headers);


        String[] values = new String[rowNum];
        while (rs.next()){
            for (int i = 1; i <= rowNum ; i++) {
                values[i-1] = rs.getString(i);
            }
            System.out.printf(format, values);
        }

    }

}
