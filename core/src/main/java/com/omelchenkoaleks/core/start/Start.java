package com.omelchenkoaleks.core.start;

import com.omelchenkoaleks.core.dao.implementations.StorageDAOImpl;
import com.omelchenkoaleks.core.decorator.StorageSync;
import com.omelchenkoaleks.core.exceptions.CurrencyException;
import com.omelchenkoaleks.core.interfaces.Storage;

import java.util.Currency;

public class Start {

    public static void main(String[] args) {

        StorageSync storageSync = new StorageSync(new StorageDAOImpl());

        Storage s = storageSync.get(10);

        try {
            storageSync.addCurrency(s, Currency.getInstance("USD"));
        } catch (CurrencyException e) {
            e.printStackTrace();
        }

    }
}
