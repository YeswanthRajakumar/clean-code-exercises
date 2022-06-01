package com.b.simple.design.business.customer;

import java.util.List;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.AmountI;
import com.b.simple.design.model.customer.ProductI;

public interface CustomerBOI {

	AmountI getSumOfProducts(List<ProductI> products)
			throws DifferentCurrenciesException;

}