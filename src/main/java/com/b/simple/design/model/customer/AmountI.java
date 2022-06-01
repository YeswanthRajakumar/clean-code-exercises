package com.b.simple.design.model.customer;

import java.math.BigDecimal;

public interface AmountI {
	BigDecimal getValue();

	Currency getCurrency();
}
