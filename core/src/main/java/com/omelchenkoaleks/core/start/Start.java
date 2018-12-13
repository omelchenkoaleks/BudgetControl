package com.omelchenkoaleks.core.start;

import com.omelchenkoaleks.core.dao.implementations.StorageDAOImpl;
import com.omelchenkoaleks.core.database.SQLiteConnection;
import com.omelchenkoaleks.core.decorator.StorageSynchronizer;
import com.omelchenkoaleks.core.exceptions.CurrencyException;
import com.omelchenkoaleks.core.implementations.DefaultStorage;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Currency;

public class Start {



    // тест на добавление валюты в банк ВТБ

    public static void main(String[] args) {

        StorageSynchronizer storageSynс = new StorageSynchronizer(new StorageDAOImpl());
        DefaultStorage tmpStore = (DefaultStorage) storageSynс.getAll().get(1).getChilds().get(0);

        try {
            storageSynс.addCurrency(tmpStore, Currency.getInstance("USD"));
            System.out.println("storageSynс.getAll() = " + storageSynс.getAll());
        } catch (CurrencyException e) {
            e.printStackTrace();
        }

    }








    // тест второго слоя реализация базы данных

//    public static void main(String[] args) {
//
//        new StorageDAOImpl().getAll();
//
//    }






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
