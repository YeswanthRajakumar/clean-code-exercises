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
    private final CustomerBO customerBO = new CustomerBOImpl();

    @Test
    public void testShouldAddTwoProductsSameCurrencies() throws DifferentCurrenciesException {
        AmountImpl amount5_0 = getAmount("5.0");
        AmountImpl amount6_0 = getAmount("6.0");
        AmountImpl[] amounts = new AmountImpl[]{amount5_0, amount6_0};
        ArrayList<Product> products = getProductsWithAmount(amounts);

        Amount actualAmount = customerBO.getCustomerProductsSum(products);

        assertCurrency(new BigDecimal("11.0"), actualAmount);
    }

    private void assertCurrency(BigDecimal expectedAmount, Amount actualAmount) {
        assertEquals(expectedAmount, actualAmount.getValue());
        assertEquals(EURO, actualAmount.getCurrency());
    }

    private ArrayList<Product> getProductsWithAmount(AmountImpl[] amounts) {
        ArrayList<Product> products = new ArrayList<Product>();
        for (AmountImpl amount : amounts) {
            products.add(getProduct(amount));
        }
        return products;
    }

    private ProductImpl getProduct(AmountImpl amount) {
        return new ProductImpl(100, "Product 15", ProductType.BANK_GUARANTEE, amount);
    }

    private AmountImpl getAmount(String value) {
        return new AmountImpl(new BigDecimal(value), CustomerBOTest.EURO);
    }

    @Test
    public void testShouldThrowException_whenDifferentCurrenciesGotAdded() {
        AmountImpl amount5_0 = new AmountImpl(new BigDecimal("5.0"), Currency.INDIAN_RUPEE);
        AmountImpl amount6_0 = new AmountImpl(new BigDecimal("6.0"), Currency.EURO);
        AmountImpl[] amounts = new AmountImpl[]{amount5_0, amount6_0};
        ArrayList<Product> products = getProductsWithAmount(amounts);
        Assertions.assertThrows(DifferentCurrenciesException.class, () -> customerBO.getCustomerProductsSum(products));
    }

    @Test
    public void testShouldReturnZeroWhenProductsListIsZero() throws DifferentCurrenciesException {
        List<Product> products = new ArrayList<Product>();
        Amount temp = customerBO.getCustomerProductsSum(products);
        assertEquals(EURO, temp.getCurrency());
        assertEquals(BigDecimal.ZERO, temp.getValue());
    }

}