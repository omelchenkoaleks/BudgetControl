package com.omelchenkoaleks.core.interfaces;

import com.omelchenkoaleks.core.exceptions.AmountException;
import com.omelchenkoaleks.core.exceptions.CurrencyException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;

// TODO: изменить тип BigDecimal на готовый класс по работе с деньгами Money
public interface Storage extends TreeNode {


    /*   ПОЛУЧЕНИЕ БАЛАНСА   */

    // остаток по каждой валюте в хранилище
    Map<Currency, BigDecimal> getCurrencyAmounts();

    // остаток по каждой валюте в хранилище
    BigDecimal getAmount(Currency currency) throws CurrencyException;

    // примерный остаток в переводе всех денег в одну валюту
    BigDecimal getApproxAmount(Currency currency) throws CurrencyException;


    /*   ИЗМЕНЕНИЕ БАЛАНСА (ОСТАТКА)   */

    // изменение баланса по определенной валюте
    void changeAmount(BigDecimal amount, Currency currency) throws CurrencyException;

    // добавить сумму в валюте
    void addAmount(BigDecimal amount, Currency currency) throws CurrencyException;

    // отнять сумму в валюте
    void expenseAmount(BigDecimal amount, Currency currency)
            throws CurrencyException, AmountException;


    /*   РАБОТА С ВАЛЮТОЙ   */

    // добавить новую валюту в хранилище
    void addCurrency(Currency currency) throws CurrencyException;

    // удалить валюту из хранилища
    void deleteCurrency(Currency currency) throws CurrencyException;

    // получить валюту по коду
    Currency getCurrency(String code) throws CurrencyException;

    // получить все доступные валюты хранилища в отдельной коллекции
    List<Currency>  getAvailableCurrencies();
}
