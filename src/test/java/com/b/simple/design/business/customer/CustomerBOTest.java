package com.b.simple.design.business.customer;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerBOTest {

    public static final Currency EURO = Currency.EURO;
    private final CustomerBOI customerBO = new CustomerBO();

    @Test
    public void testShouldAddTwoProductsSameCurrencies() throws DifferentCurrenciesException {
        Amount amount5_0 = getAmount("5.0");
        Amount amount6_0 = getAmount("6.0");
        Amount[] amounts = new Amount[]{amount5_0, amount6_0};
        ArrayList<ProductI> products = getProductsWithAmount(amounts);

        AmountI actualAmount = customerBO.getSumOfProducts(products);

        assertCurrency(new BigDecimal("11.0"), actualAmount);
    }

    private void assertCurrency(BigDecimal expectedAmount, AmountI actualAmount) {
        assertEquals(expectedAmount, actualAmount.getValue());
        assertEquals(EURO, actualAmount.getCurrency());
    }

    private ArrayList<ProductI> getProductsWithAmount(Amount[] amounts) {
        ArrayList<ProductI> products = new ArrayList<>();
        for (Amount amount : amounts) {
            products.add(getProduct(amount));
        }
        return products;
    }

    private Product getProduct(Amount amount) {
        return new Product(100, "Product 15", ProductType.BANK_GUARANTEE, amount);
    }

    private Amount getAmount(String value) {
        return new Amount(new BigDecimal(value), CustomerBOTest.EURO);
    }

    @Test
    public void testShouldThrowException_whenDifferentCurrenciesGotAdded() {
        Amount amount5_0 = new Amount(new BigDecimal("5.0"), Currency.INDIAN_RUPEE);
        Amount amount6_0 = new Amount(new BigDecimal("6.0"), Currency.EURO);
        Amount[] amounts = new Amount[]{amount5_0, amount6_0};
        ArrayList<ProductI> products = getProductsWithAmount(amounts);
        Assertions.assertThrows(DifferentCurrenciesException.class, () -> customerBO.getSumOfProducts(products));
    }

    @Test
    public void testShouldReturnZeroWhenProductsListIsZero() throws DifferentCurrenciesException {
        List<ProductI> products = new ArrayList<>();
        AmountI temp = customerBO.getSumOfProducts(products);
        assertEquals(EURO, temp.getCurrency());
        assertEquals(BigDecimal.ZERO, temp.getValue());
    }

}