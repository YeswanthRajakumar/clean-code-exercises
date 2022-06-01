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
        BigDecimal totalAmount = BigDecimal.ZERO;
        if (products.isEmpty()) return new Amount(totalAmount, Currency.EURO);

        ProductI firstProduct = products.get(0);
        Currency CurrencyTypeOfFirstProduct = getCurrencyTypeOfProduct(firstProduct);

        for (ProductI product : products) {
            if (!getCurrencyTypeOfProduct(product).equals(CurrencyTypeOfFirstProduct))
                throw new DifferentCurrenciesException();
            totalAmount = totalAmount.add(product.getAmount().getValue());
        }

        return new Amount(totalAmount, CurrencyTypeOfFirstProduct);
    }

    private Currency getCurrencyTypeOfProduct(ProductI products) {
        return products.getAmount().getCurrency();
    }

}