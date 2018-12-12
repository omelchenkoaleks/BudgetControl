package com.omelchenkoaleks.core.start;

import com.omelchenkoaleks.core.dao.implementations.StorageDAOImpl;
import com.omelchenkoaleks.core.database.SQLiteConnection;

import java.sql.ResultSet;
import java.sql.Statement;

public class Start {

    public static void main(String[] args) {

        new StorageDAOImpl().getAll();

    }






    //   тест на подключение к базе данных

//    public static void main(String[] args) {
//
//        try (Statement stmt = SQLiteConnection.getConnection().createStatement(); ResultSet rs = stmt.executeQuery("select * from storage")) {
//
//            while (rs.next()) {
//                System.out.println(rs.getString("name"));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

}
