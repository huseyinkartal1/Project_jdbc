package jdbc3;

import java.sql.*;

public class SQLite3 {

    /**
     * MySQL deki verileri JDBC ile bağlanarak SQLite veri tabanına yazdırdık.
     * @param args Java args
     * @throws SQLException hata durumunda fırlatılmıştır
     */
    public static void main(String[] args) throws SQLException {
        Connection connMysql;
        Connection connSQLite;
        Statement statMysql;
        Statement statSQLite;
        ResultSet rsMySql;
        ResultSet rsSQLite;

        connMysql = DriverManager.getConnection("jdbc:mysql://142.93.110.12:3306/tempdb",
                "gsuser",
                "Gsuser!123456");

        statMysql = connMysql.createStatement();

        String sql = "SELECT * FROM kartal";

        rsMySql = statMysql.executeQuery(sql);

        connSQLite = DriverManager.getConnection("jdbc:sqlite:src/data.sqlite");

        statSQLite = connSQLite.createStatement();

        while (rsMySql.next()) {

            String sqlINSERT = "INSERT INTO kartal VALUES('" +
                    rsMySql.getString(1) + "','" +
                    rsMySql.getString(2) + "'," +
                    rsMySql.getString(3) + " , '" +
                    rsMySql.getString(4) + "')";
            statSQLite.executeUpdate(sqlINSERT);
        }

        rsSQLite = statSQLite.executeQuery(sql);

        while (rsSQLite.next()) {

            System.out.printf("%s\t%s\t%s\t%s\n",
                    rsSQLite.getString(1),
                    rsSQLite.getString(2),
                    rsSQLite.getString(3),
                    rsSQLite.getString(4));
        }

        connSQLite.close();
        connMysql.close();
        statMysql.close();
        statSQLite.close();

    }
}
