package com.b.simple.design.business.customer;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountI;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.ProductI;

import java.math.BigDecimal;
import java.util.List;

public class CustomerBO implements CustomerBOI {

    @Override
    public AmountI getSumOfProducts(List<ProductI> products) throws DifferentCurrenciesException {
        if (products.isEmpty())
            return new Amount(BigDecimal.ZERO, Currency.EURO);
        if (!isSameTypeOfCurrency(products))
            throw new DifferentCurrenciesException();
        return calculateSumOfProducts(products);
    }

    private Amount calculateSumOfProducts(List<ProductI> products) {
        Currency currencyTypeOfFirstProduct = getCurrencyTypeOfProduct(products.get(0));
        BigDecimal sumOfProducts = products.stream()
                .map(product -> product.getAmount().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Amount(sumOfProducts, currencyTypeOfFirstProduct);
    }

    private boolean isSameTypeOfCurrency(List<ProductI> products) {
        Currency currencyTypeOfFirstProduct = getCurrencyTypeOfProduct(products.get(0));
        return products.stream()
                .map(product -> product.getAmount().getCurrency())
                .allMatch(currencyType -> currencyType.equals(currencyTypeOfFirstProduct));
    }

    private Currency getCurrencyTypeOfProduct(ProductI product) {
        return product.getAmount().getCurrency();
    }

}