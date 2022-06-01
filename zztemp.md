```java
package com.b.simple.design.business.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.b.simple.design.model.customer.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.AmountI;

public class CustomerBOTestRefactored {

    private CustomerBOI clientBO = new CustomerBOImplRefactored();

    @Test
    public void testCustomerProductSum_AllProductsSameCurrency()
            throws DifferentCurrenciesException {

        AmountI[] amounts = {
                new Amount(new BigDecimal("5.0"), Currency.EURO),
                new Amount(new BigDecimal("6.0"), Currency.EURO)};

        AmountI expected = new Amount(new BigDecimal("11.0"), Currency.EURO);

        List<ProductI> products = createProductListWithAmounts(amounts);

        AmountI actual = clientBO.getSumOfProducts(products);

        assertAmount(actual, expected);
    }

    @Test
    public void testCustomerProductSum_DifferentCurrencies_ThrowsException()
            throws DifferentCurrenciesException {

        AmountI[] amounts = {
                new Amount(new BigDecimal("5.0"), Currency.EURO),
                new Amount(new BigDecimal("6.0"), Currency.INDIAN_RUPEE)};

        List<ProductI> products = createProductListWithAmounts(amounts);

        Assertions.assertThrows(DifferentCurrenciesException.class, () -> {
            @SuppressWarnings("unused")
            AmountI actual = clientBO.getSumOfProducts(products);
        });
    }

    @Test
    public void testCustomerProductSum_NoProducts()
            throws DifferentCurrenciesException {

        AmountI[] amounts = {};
        AmountI expected = new Amount(BigDecimal.ZERO, Currency.EURO);

        List<ProductI> products = createProductListWithAmounts(amounts);

        AmountI actual = clientBO.getSumOfProducts(products);


        assertAmount(actual, expected);
    }

    private void assertAmount(AmountI actual, AmountI expected) {
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getValue(), actual.getValue());
    }

    private List<ProductI> createProductListWithAmounts(AmountI[] amounts) {
        List<ProductI> products = new ArrayList<ProductI>();
        for (AmountI amount : amounts) {
            products.add(new Product(100, "Product 15",
                    ProductType.BANK_GUARANTEE, amount));
        }
        return products;
    }

}
```