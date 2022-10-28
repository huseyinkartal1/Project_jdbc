package utilities;

import java.sql.*;

public class ConnectionManager {

    //sadece connection üretecek bir driver
    //connection string
    //veri tabani ismi
    //username, password
    //jdbc:mysql://142.93.110.12/3306:tempdb


    Connection conn;
    Statement stat;
    ResultSet rs;

    String connString;
    String dbName;
    int port;
    String userName;
    String password;

    public ConnectionManager(String connString, String userName, String password) throws SQLException {

        this.connString = connString;
        this.userName = userName;
        this.password = password;

        conn= DriverManager.getConnection(connString,userName,password);
        stat=conn.createStatement();
    }

    public  ConnectionManager(String connString)  {

        this.connString = connString;

        try {
            conn= DriverManager.getConnection(connString);
            stat=conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ResultSet getResultSet(String sql){
        try {
            return stat.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void close(){
        try {
            conn.close();
            stat.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
