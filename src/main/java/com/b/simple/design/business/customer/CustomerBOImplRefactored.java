package com.b.simple.design.business.customer;

import java.math.BigDecimal;
import java.util.List;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.AmountI;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.ProductI;

public class CustomerBOImplRefactored implements CustomerBOI {

	@Override
	public AmountI getSumOfProducts(List<ProductI> products)
			throws DifferentCurrenciesException {

		if (products.size() == 0)
			return new Amount(BigDecimal.ZERO, Currency.EURO);

		if(!doAllProductsHaveSameCurrency(products)) {
			throw new DifferentCurrenciesException();
		}

		return calculateSumOfProducts(products);
	}

	private AmountI calculateSumOfProducts(List<ProductI> products) {
		
		Currency firstProductCurrency = products.get(0).getAmount()
				.getCurrency();

		BigDecimal sum = products.stream()
			.map(product -> product.getAmount().getValue())
			.reduce(BigDecimal.ZERO, BigDecimal::add);

		return new Amount(sum, firstProductCurrency);
	}

	private boolean doAllProductsHaveSameCurrency(List<ProductI> products) throws DifferentCurrenciesException {

		Currency firstProductCurrency = products.get(0).getAmount()
				.getCurrency();

		return products.stream()
				.map(product -> product.getAmount().getCurrency())
				.allMatch(currency -> currency.equals(firstProductCurrency));
			
	}

}