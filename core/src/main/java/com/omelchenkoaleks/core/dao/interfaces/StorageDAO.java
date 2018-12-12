package com.omelchenkoaleks.core.dao.interfaces;

import com.omelchenkoaleks.core.interfaces.Storage;

import java.math.BigDecimal;
import java.util.Currency;

public interface StorageDAO extends CommonDAO<Storage> {

    boolean addCurrency(Storage storage, Currency currency);
    boolean deleteCurrency(Storage storage, Currency currency);
    boolean updateAmount(Storage storage, BigDecimal amount);
}
