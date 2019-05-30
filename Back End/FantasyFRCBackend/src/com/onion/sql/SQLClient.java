package com.onion.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class SQLClient {

    private static SQLClient instance;

    public static SQLClient getInstance() {
        if (instance == null) {
            instance = new SQLClient();
        }
        return instance;
    }

    private final String username = "root";
    private final String password = "password";
    private final String url = "jdbc:mysql://localhost:3306/userpass";

    private SQLClient() {
        getCon();
    }

    protected void reconnect() {
        getCon();
    }

    private Connection getCon() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("couldn't connect!");
        }
        return null;
    }

    public void writeUser(final String username, final String password){
        try {
            new SQLWriteObject(username, password).writeObject(Objects.requireNonNull(getCon()).createStatement());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String readUser(final String username){
        try {
            return new SQLReadObject(username).readObject(Objects.requireNonNull(getCon()).createStatement());
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    //TODO Clean up try catch
    private class SQLReadObject{

        private final String sqlString;
        private String password;

        SQLReadObject(final String username){
            sqlString = "SELECT password from userdata WHERE username = '" + username + "'";
        }

        String readObject(final Statement statement){
            try {
                ResultSet result = statement.executeQuery(sqlString);
                if(result.first()){
                    password = result.getString("password");
                    return password;
                }
                return null;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }finally {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class SQLWriteObject{

        private final String sqlString;

        SQLWriteObject(final String username, final String password){
            sqlString = "INSERT INTO userdata(username, password) values('" + username + "', '" + password + "')";
        }

        void writeObject(final Statement statement){
            try {
                statement.executeUpdate(sqlString);
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
