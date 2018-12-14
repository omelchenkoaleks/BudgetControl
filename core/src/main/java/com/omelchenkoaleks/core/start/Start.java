package com.omelchenkoaleks.core.start;

import com.omelchenkoaleks.core.dao.impls.OperationDAOImpl;
import com.omelchenkoaleks.core.dao.impls.SourceDAOImpl;
import com.omelchenkoaleks.core.dao.impls.StorageDAOImpl;
import com.omelchenkoaleks.core.decorator.OperationSync;
import com.omelchenkoaleks.core.decorator.SourceSync;
import com.omelchenkoaleks.core.decorator.StorageSync;
import com.omelchenkoaleks.core.enums.OperationType;
import com.omelchenkoaleks.core.exceptions.CurrencyException;
import com.omelchenkoaleks.core.impls.DefaultSource;
import com.omelchenkoaleks.core.impls.DefaultStorage;
import com.omelchenkoaleks.core.interfaces.Source;
import com.omelchenkoaleks.core.interfaces.Storage;

import java.math.BigDecimal;
import java.util.Currency;

public class Start {

    public static void main(String[] args) {

        StorageSync storageSync = new StorageSync(new StorageDAOImpl());
        SourceSync sourceSync = new SourceSync(new SourceDAOImpl());
        OperationSync operationSync = new OperationSync(
                new OperationDAOImpl(sourceSync.getIdentityMap(), storageSync.getIdentityMap()), sourceSync, storageSync);

        testSource(sourceSync);
        testStorage(storageSync);

    }

    private static void testStorage(StorageSync storageSync) {
        Storage parentStorage = storageSync.get(10);

        DefaultStorage storage = new DefaultStorage("def store");

        try {
            storage.addCurrency(Currency.getInstance("USD"), new BigDecimal(145));
            storage.addCurrency(Currency.getInstance("RUB"), new BigDecimal(100));

            storage.setParent(parentStorage);

            storageSync.add(storage);

            storageSync.deleteCurrency(storage, Currency.getInstance("USD"));


        } catch (CurrencyException e) {
            e.printStackTrace();
        }
    }

    private static void testSource(SourceSync sourceSync) {
        Source parentSource = sourceSync.get(4);

        DefaultSource s = new DefaultSource("test source 2");
        s.setOperationType(OperationType.OUTCOME);
        s.setParent(parentSource);

        sourceSync.add(s);
        System.out.println("sourceSync = " + sourceSync.getAll());
    }
}
