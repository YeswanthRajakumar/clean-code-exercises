package com.b.simple.design.model.customer;

import java.math.BigDecimal;

public class Amount implements AmountI {

    BigDecimal value;
    Currency currency;

    public Amount(BigDecimal value, Currency currency) {
        super();
        this.value = value;
        this.currency = currency;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

}
