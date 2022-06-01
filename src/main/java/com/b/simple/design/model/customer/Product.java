package com.b.simple.design.model.customer;

/**
 * Collateral Model Object.
 */
public class Product implements ProductI {
    private long id;

    private String name;

    private ProductType type;

    private AmountI amount;

    public Product(long id, String name, ProductType type, AmountI amount) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @Override
    public AmountI getAmount() {
        return amount;
    }

    public void setAmount(AmountI amount) {
        this.amount = amount;
    }
}
