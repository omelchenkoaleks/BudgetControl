package com.omelchenkoaleks.core.implementations.operations;

import com.omelchenkoaleks.core.abstracts.AbstractOpetation;
import com.omelchenkoaleks.core.interfaces.Source;
import com.omelchenkoaleks.core.interfaces.Storage;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;

// TODO: для всех классов создать конструкторы без поля id, т.к. у  нас будет autoincrement
public class OutcomeOperation extends AbstractOpetation {

    private Storage fromStorage;
    private Source toSource;
    private BigDecimal amount;
    private Currency currency;


    public OutcomeOperation(Storage fromStorage, Source toSource, BigDecimal amount, Currency currency) {
        this.fromStorage = fromStorage;
        this.toSource = toSource;
        this.amount = amount;
        this.currency = currency;
    }

    public OutcomeOperation(long id, Calendar dateTime, String addInfo, Storage fromStorage, Source toSource, BigDecimal amount, Currency currency) {
        super(id, dateTime, addInfo);
        this.fromStorage = fromStorage;
        this.toSource = toSource;
        this.amount = amount;
        this.currency = currency;
    }

    public OutcomeOperation(long id, Storage fromStorage, Source toSource, BigDecimal amount, Currency currency) {
        super(id);
        this.fromStorage = fromStorage;
        this.toSource = toSource;
        this.amount = amount;
        this.currency = currency;
    }

    public Storage getFromStorage() {
        return fromStorage;
    }

    public void setFromStorage(Storage fromStorage) {
        this.fromStorage = fromStorage;
    }

    public Source getToSource() {
        return toSource;
    }

    public void setToSource(Source toSource) {
        this.toSource = toSource;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
