package com.omelchenkoaleks.core.start;

import com.omelchenkoaleks.core.exceptions.CurrencyException;
import com.omelchenkoaleks.core.implementations.DefaultStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Currency;

public class Start {

//    Connection connection;
//
//    public static void main(String[] args) {
//
//        Start start = new Start();
//
//        if (start.open()) {
//            start.select();
//        }
//    }
//
//    boolean open() {
//
//        try {
//
//            Class.forName("org.sqlite.JDBC");
//            connection = DriverManager.getConnection(
//                    "jdbc:sqlite:C:\\SQLite\\money.db");
//
//            System.out.println("Connected");
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        return true;
//    }
//
//    void select() {
//
//        try {
//
//            Statement statement = connection.createStatement();
//            String query = "SELECT id, name, parent_id FROM money";
//            ResultSet rs = statement.executeQuery(query);
//
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                int parent_id = rs.getInt("parent_id");
//                System.out.println(id + "\t" + name + "\t" + parent_id);
//            }
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
