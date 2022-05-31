package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.a.introduction.gildedrose.GildedRoseTestUtilities.*;


@Nested
public class GildedRoseItemTest {
    public static final int NON_EXPIRED_SELLIN = 15;
    public static final int NON_EXPIRED_QUALITY = 3;
    public static final int EXPIRED_SELLIN = -1;
    public static final int EXPIRED_QUALITY = 4;
    public static final int MAX_QUALITY = 50;
    public static final int SELLIN_GREATER_THAN_10 = 15;
    public static final int QUALITY = 3;
    public static final int SELLIN_LESS_THAN_10 = 7;
    private static final int SELLIN_LESS_THAN_5 = 4;
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String DEFAULT_ITEM = "DEFAULT_ITEM";

    @Nested
    class DefaultItemTest {


        @Nested
        class NonExpiredItem {
            @Test
            public void testUpdateQualityForNonExpiredDefaultItemWhereQualityDecreasesByOne() {
                GildedRose app = getGildedRoseWithOneItem(DEFAULT_ITEM, NON_EXPIRED_SELLIN, NON_EXPIRED_QUALITY);
                app.updateQuality();
                Item expectedItem = getItem(DEFAULT_ITEM, NON_EXPIRED_SELLIN - 1, NON_EXPIRED_QUALITY - 1);
                Item actualItem = app.items[0];
                assertItem(expectedItem, actualItem);
            }
        }

        @Nested
        class ExpiredItem {
            @Test
            public void testUpdateQualityForExpiredDefaultItemWhereQualityDecreasesByTwo() {
                GildedRose app = getGildedRoseWithOneItem(DEFAULT_ITEM, EXPIRED_SELLIN, EXPIRED_QUALITY);
                app.updateQuality();
                Item expectedItem = getItem(DEFAULT_ITEM, EXPIRED_SELLIN - 1, EXPIRED_QUALITY - 2);
                Item actualItem = app.items[0];
                assertItem(expectedItem, actualItem);
            }
        }

    }

    @Nested
    class AgedBrieTest {


        @Test
        public void testUpdateQualityForNonExpiredAgedBrieIncreaseQualityByOneWhenSellinDecreaseByOne() {

            GildedRose app = getGildedRoseWithOneItem(AGED_BRIE, NON_EXPIRED_SELLIN, NON_EXPIRED_QUALITY);
            Item expectedItem = getItem(AGED_BRIE, NON_EXPIRED_SELLIN - 1, NON_EXPIRED_QUALITY + 1);
            Item actualItem = app.items[0];
            app.updateQuality();
            assertItem(expectedItem, actualItem);
        }

        @Test
        public void testUpdateQualityForExpiredAgedBrieIncreaseQualityByTwoWhenSellinDecreaseByOne() {
            int quality = 3;
            GildedRose app = getGildedRoseWithOneItem(AGED_BRIE, EXPIRED_SELLIN, quality);
            Item expectedItem = getItem(AGED_BRIE, EXPIRED_SELLIN - 1, quality + 2);
            Item actualItem = app.items[0];
            app.updateQuality();
            assertItem(expectedItem, actualItem);
        }

        @Test
        public void testUpdateQualityForNonExpiredAgedBrieDoesNotIncreaseQualityWhenAtTheMaxQualityAndSellinDecreaseByOne() {
            GildedRose app = getGildedRoseWithOneItem(AGED_BRIE, NON_EXPIRED_SELLIN, MAX_QUALITY);
            Item expectedItem = getItem(AGED_BRIE, NON_EXPIRED_SELLIN - 1, MAX_QUALITY);
            Item actualItem = app.items[0];
            app.updateQuality();
            assertItem(expectedItem, actualItem);
        }
    }

    @Nested
    class BackstagePassesTest {


        @Test
        public void testUpdateQualityBackstagePassesWhenSellinIsGreaterThanTen() {
            GildedRose app = getGildedRoseWithOneItem(BACKSTAGE_PASSES, SELLIN_GREATER_THAN_10, QUALITY);
            app.updateQuality();

            Item actualItem = app.items[0];
            Item expectedItem = new Item(BACKSTAGE_PASSES, SELLIN_GREATER_THAN_10 - 1, QUALITY + 1);

            assertItem(expectedItem, actualItem);
        }

        @Test
        public void testUpdateQualityBackstagePassesWhenSellinIsLessThanTen() {
            GildedRose app = getGildedRoseWithOneItem(BACKSTAGE_PASSES, SELLIN_LESS_THAN_10, QUALITY);
            app.updateQuality();

            Item actualItem = app.items[0];
            Item expectedItem = new Item(BACKSTAGE_PASSES, SELLIN_LESS_THAN_10 - 1, QUALITY + 2);

            assertItem(expectedItem, actualItem);

        }

        @Test
        public void testUpdateQualityBackstagePassesWhenSellinIsLessThanFive() {
            GildedRose app = getGildedRoseWithOneItem(BACKSTAGE_PASSES, SELLIN_LESS_THAN_5, QUALITY);
            app.updateQuality();

            Item actualItem = app.items[0];
            Item expectedItem = new Item(BACKSTAGE_PASSES, SELLIN_LESS_THAN_5 - 1, QUALITY + 3);

            assertItem(expectedItem, actualItem);
        }

    }

}