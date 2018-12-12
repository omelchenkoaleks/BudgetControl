package com.omelchenkoaleks.core;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;

// TODO: изменить тип BigDecimal на готовый класс по работе с деньгами Money

public interface Storage {

    String getName();


    /*   РАБОТА С ВАЛЮТОЙ   */

    // добавить новую валюту в хранилище
    void addCurrency(Currency currency);

    // удалить валюту из хранилища
    void deleteCurrency(Currency currency);

    // получить валюту по коду
    Currency getCurrency(String code);

    // получить все доступные валюты хранилища в отдельной коллекции
    List<Currency> getAvailableCurrencies();


    /*   ИЗМЕНЕНИЕ БАЛАНСА (ОСТАТКА)   */

    // изменение баланса по определенной валюте
    void changeAmount(BigDecimal amount, Currency currency); // throws CurrencyException;

    // добавить сумму в валюте
    void addAmount(BigDecimal amount, Currency currency); // throws CurrencyException;

    // отнять сумму в валюте
    void expenseAmount(BigDecimal amount, Currency currency);
           // throws CurrencyException, AmountException;


    /*   ПОЛУЧЕНИЕ БАЛАНСА   */

    // остаток по каждой валюте в хранилище
    Map<Currency, BigDecimal> getCurrencyAmounts();

    // остаток по каждой валюте в хранилище
    BigDecimal getAmount(Currency currency); // throws CurrencyException;

    // примерный остаток в переводе всех денег в одну валюту
    BigDecimal getApproxAmount(Currency currency); // throws CurrencyException;
}
