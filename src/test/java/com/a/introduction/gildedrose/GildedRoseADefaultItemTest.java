package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseADefaultItemTest {

    public static final String DEFAULT_ITEM = "DEFAULT_ITEM";

    @Test
    public void testUpdateQualityForOneNonExpiredDefaultItemWhereQualityDecreasesByOne() {
        GildedRose app = getGildedRoseWithOneItem(15, 3);
        app.updateQuality();
        Item expectedItem = getItem(14, 2);
        Item actualItem = app.items[0];
        assertItem(expectedItem, actualItem);
    }

    @Test
    public void testUpdateQualityForOneExpiredDefaultItemWhereQualityDecreasesByTwo() {
        GildedRose app = getGildedRoseWithOneItem(-1, 4);
        app.updateQuality();
        Item expectedItem = getItem(-2, 2);
        Item actualItem = app.items[0];
        assertItem(expectedItem, actualItem);
    }

    private Item getItem(int sellIn, int quality) {
        return new Item(GildedRoseADefaultItemTest.DEFAULT_ITEM, sellIn, quality);
    }

    private void assertItem(Item expectedItem, Item actualItem) {
        assertEquals(expectedItem.name, actualItem.name);
        assertEquals(expectedItem.sellIn, actualItem.sellIn);
        assertEquals(expectedItem.quality, actualItem.quality);
    }

    private GildedRose getGildedRoseWithOneItem(int initialSellin, int initialQuality) {
        Item item = getItem(initialSellin, initialQuality);
        Item[] items = new Item[]{item};
        return new GildedRose(items);
    }
}