package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.a.introduction.gildedrose.GildedRoseTestUtilities.*;

import org.junit.jupiter.api.Test;

public class GildedRoseAgedBrieTest {

    public static final int NON_EXPIRED_SELLIN = 4;
    public static final int NON_EXPIRED_QUALITY = 3;
    public static final int EXPIRED_SELLIN = -1;
    public static final int MAX_QUALITY = 50;

    @Test
    public void testUpdateQualityForNonExpiredAgedBrieIncreaseQualityByOneWhenSellinDecreaseByOne() {

        GildedRose app = getGildedRoseWithOneItem("Aged Brie", NON_EXPIRED_SELLIN, NON_EXPIRED_QUALITY);
        Item expectedItem = getItem("Aged Brie", NON_EXPIRED_SELLIN - 1, NON_EXPIRED_QUALITY + 1);
        Item actualItem = app.items[0];
        app.updateQuality();
        assertItem(expectedItem, actualItem);
    }

    @Test
    public void testUpdateQualityForExpiredAgedBrieIncreaseQualityByTwoWhenSellinDecreaseByOne() {
        int quality = 3;
        GildedRose app = getGildedRoseWithOneItem("Aged Brie", EXPIRED_SELLIN, quality);
        Item expectedItem = getItem("Aged Brie", EXPIRED_SELLIN - 1, quality + 2);
        Item actualItem = app.items[0];
        app.updateQuality();
        assertItem(expectedItem, actualItem);
    }

    @Test
    public void testUpdateQualityForNonExpiredAgedBrieDoesNotIncreaseQualityWhenAtTheMaxQualityAndSellinDecreaseByOne() {
        GildedRose app = getGildedRoseWithOneItem("Aged Brie", NON_EXPIRED_SELLIN, MAX_QUALITY);
        Item expectedItem = getItem("Aged Brie", NON_EXPIRED_SELLIN - 1, MAX_QUALITY);
        Item actualItem = app.items[0];
        app.updateQuality();
        assertItem(expectedItem, actualItem);
    }
}
