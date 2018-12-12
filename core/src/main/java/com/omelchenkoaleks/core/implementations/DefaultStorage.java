package com.omelchenkoaleks.core.implementations;

import com.omelchenkoaleks.core.abstracts.AbstractTreeNode;
import com.omelchenkoaleks.core.exceptions.AmountException;
import com.omelchenkoaleks.core.exceptions.CurrencyException;
import com.omelchenkoaleks.core.interfaces.Storage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultStorage extends AbstractTreeNode implements Storage {

    // сразу инициализируем пустые коллекции, потому что хоть одна валюта будет
    private Map<Currency, BigDecimal> currencyAmounts = new HashMap<Currency, BigDecimal>();
    private  List<Currency> currencyList = new ArrayList<>();


    /*   КОНСТРУКТОРЫ   */

    public DefaultStorage() {}

    public DefaultStorage(String name) {
        super(name);
    }

    public DefaultStorage(long id, String name) {
        super(id, name);
    }

    public DefaultStorage(String name, Map<Currency, BigDecimal> currencyAmounts, List<Currency> currencyList) {
        super(name);
        this.currencyAmounts = currencyAmounts;
        this.currencyList = currencyList;
    }

    public DefaultStorage(Map<Currency, BigDecimal> currencyAmounts) {
        this.currencyAmounts = currencyAmounts;
    }

    public DefaultStorage(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }


    /*   ГЕТЕРЫ И СЕТЕРЫ   */

    @Override
    public Map<Currency, BigDecimal> getCurrencyAmounts() {
        return currencyAmounts;
    }

    public void setCurrencyAmounts(Map<Currency, BigDecimal> currencyAmounts) {
        this.currencyAmounts = currencyAmounts;
    }


    public List<Currency> getAvailableCurrencies() {
        return currencyList;
    }

    public void setAvailableCurrencies(List<Currency> availableCurrencies) {
        currencyList = availableCurrencies;
    }



    /*   ОПЕРАЦИИ С ВАЛЮТОЙ   */

    @Override
    public void addCurrency(Currency currency) throws CurrencyException {

        if (currencyAmounts.containsKey(currency)) {
            throw new CurrencyException("Currency already exist");
        }

        currencyList.add(currency);
        currencyAmounts.put(currency, BigDecimal.ZERO);
    }


    @Override
    public void deleteCurrency(Currency currency) throws CurrencyException {

        checkCurrencyExist(currency);

        // не даем удалить валюту, если в хранилище есть деньги по этой валюте
        if (!currencyAmounts.get(currency).equals(BigDecimal.ZERO)) {
            throw new CurrencyException("Can`t delete currency with amount");
        }

        currencyAmounts.remove(currency);
        currencyList.remove(currency);
    }


    @Override
    public BigDecimal getApproxAmount(Currency currency) {
        // TODO: реализовать расчет остатка с приведением в одну валюту
        throw new UnsupportedOperationException("Not implemented");
    }


    @Override
    public Currency getCurrency(String code) throws CurrencyException {
        // количество валют для каждого хранилища будет небольшим = поэтому
        // можно проводить поиск через цикл
        // можно использовать библиотеку Apache Commons Collections

        for (Currency currency: currencyList) {
            if (currency.getCurrencyCode().equals(code)) {
                return currency;
            }
        }

        throw new CurrencyException("Currency (code="+code+") not exist in storage");
    }


    /*   РАБОТА С БАЛАНСОМ   */

    // отнимаем деньги из хранилища
    @Override
    public void expenseAmount(BigDecimal amount, Currency currency)
            throws CurrencyException, AmountException {

        checkCurrencyExist(currency);

        BigDecimal oldAmount = currencyAmounts.get(currency);
        BigDecimal newValue = oldAmount.subtract(amount);
        checkAmount(newValue); // не даем балансу уйти в минус
        currencyAmounts.put(currency, newValue);
    }


    // добавление денег в хранилще
    @Override
    public void addAmount(BigDecimal amount, Currency currency) throws CurrencyException{
        checkCurrencyExist(currency);
        // сначала получаем старую сумму по ключу из Map
        BigDecimal oldAmount = currencyAmounts.get(currency);
        // и добавляем
        currencyAmounts.put(currency, oldAmount.add(amount));
    }

    // ручное обновление баланса
    @Override
    public void changeAmount(BigDecimal amount, Currency currency) throws CurrencyException {
        checkCurrencyExist(currency);
        currencyAmounts.put(currency, amount);
    }

    @Override
    public BigDecimal getAmount(Currency currency)throws CurrencyException{
        checkCurrencyExist(currency);
        return currencyAmounts.get(currency);
    }


    // проверка, есть ли такая валюта в данном хранилище
    private void checkCurrencyExist(Currency currency) throws CurrencyException {
        if (!currencyAmounts.containsKey(currency)) {
            throw new CurrencyException("Currency " + currency + " not exist");
        }
    }

    // сумма не должна быть меньше нуля (в реальности такое невозможно,
    // мы не можем потратить больш, чем у нас есть)
    private void checkAmount(BigDecimal amount) throws AmountException {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new AmountException("Amount can`t be < 0");
        }
    }
}
