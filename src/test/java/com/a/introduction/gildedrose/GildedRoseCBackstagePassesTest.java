package com.a.introduction.gildedrose;

import static com.a.introduction.gildedrose.GildedRoseTestUtilities.assertItem;
import static com.a.introduction.gildedrose.GildedRoseTestUtilities.getGildedRoseWithOneItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseCBackstagePassesTest {
    public static final int SELLIN_GREATER_THAN_10 = 15;
    public static final int QUALITY = 3;
    public static final int SELLIN_LESS_THAN_10 = 7;
    private static final int SELLIN_LESS_THAN_5 = 4;

    @Test
    public void testUpdateQualityBackstagePassesWhenSellinIsGreaterThanTen() {
        GildedRose app = getGildedRoseWithOneItem("Backstage passes to a TAFKAL80ETC concert", SELLIN_GREATER_THAN_10, QUALITY);
        app.updateQuality();

        Item actualItem = app.items[0];
        Item expectedItem = new Item("Backstage passes to a TAFKAL80ETC concert", SELLIN_GREATER_THAN_10 - 1, QUALITY + 1);

        assertItem(expectedItem, actualItem);
    }

    @Test
    public void testUpdateQualityBackstagePassesWhenSellinIsLessThanTen() {
        GildedRose app = getGildedRoseWithOneItem("Backstage passes to a TAFKAL80ETC concert", SELLIN_LESS_THAN_10, QUALITY);
        app.updateQuality();

        Item actualItem = app.items[0];
        Item expectedItem = new Item("Backstage passes to a TAFKAL80ETC concert", SELLIN_LESS_THAN_10 - 1, QUALITY + 2);

        assertItem(expectedItem, actualItem);

    }

    @Test
    public void testUpdateQualityBackstagePassesWhenSellinIsLessThanFive() {
        GildedRose app = getGildedRoseWithOneItem("Backstage passes to a TAFKAL80ETC concert", SELLIN_LESS_THAN_5, QUALITY);
        app.updateQuality();

        Item actualItem = app.items[0];
        Item expectedItem = new Item("Backstage passes to a TAFKAL80ETC concert", SELLIN_LESS_THAN_5 - 1, QUALITY + 3);

        assertItem(expectedItem, actualItem);
    }

}