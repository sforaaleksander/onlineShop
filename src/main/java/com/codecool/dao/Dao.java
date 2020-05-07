package com.codecool.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.codecool.ui.UI;

public abstract class Dao {
    protected Connection connection;
    protected Statement statement;
    protected UI ui = new UI();

    public static final String DB_NAME = "src/main/resources/online_shop.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(CONNECTION_STRING);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.getStackTrace();
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database" + e.getMessage());
        }
    }

    protected void printFromDB(String query) {
        connect();
        try {
            ResultSet results = statement.executeQuery(query);
            ui.printTableFromDB(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void update(String table, int id, String column, String newValue) {
        String statement = "UPDATE " + table + " SET " + column + " = " + newValue + " WHERE Id = " + id + ";";

        try (PreparedStatement pstmt = connection.prepareStatement(statement)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
