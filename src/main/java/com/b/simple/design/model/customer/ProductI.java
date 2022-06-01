package com.b.simple.design.model.customer;

public interface ProductI {

    long getId();

    String getName();

    ProductType getType();

    AmountI getAmount();
}
