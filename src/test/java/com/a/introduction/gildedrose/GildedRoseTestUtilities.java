package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseTestUtilities {
    protected static Item getItem(String itemName, int sellIn, int quality) {
        return new Item(itemName, sellIn, quality);
    }

    protected static void assertItem(Item expectedItem, Item actualItem) {
        assertEquals(expectedItem.name, actualItem.name);
        assertEquals(expectedItem.sellIn, actualItem.sellIn);
        assertEquals(expectedItem.quality, actualItem.quality);
    }

    protected static GildedRose getGildedRoseWithOneItem(String itemName, int sellin, int quality) {
        Item item = getItem(itemName, sellin, quality);
        Item[] items = new Item[]{item};
        return new GildedRose(items);
    }
}
