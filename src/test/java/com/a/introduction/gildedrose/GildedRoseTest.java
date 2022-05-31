package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.a.introduction.gildedrose.GildedRoseTestUtilities.*;


@Nested
class DefaultItem {
    public static class GildedRoseADefaultItemTest {

        public static final int NON_EXPIRED_SELLIN = 15;
        public static final int NON_EXPIRED_QUALITY = 3;
        public static final int EXPIRED_SELLIN = -1;
        public static final int EXPIRED_QUALITY = 4;

        @Nested
        class NonExpiredItem {
            @Test
            public void testUpdateQualityForNonExpiredDefaultItemWhereQualityDecreasesByOne() {
                GildedRose app = getGildedRoseWithOneItem("DEFAULT_ITEM", NON_EXPIRED_SELLIN, NON_EXPIRED_QUALITY);
                app.updateQuality();
                Item expectedItem = getItem("DEFAULT_ITEM", NON_EXPIRED_SELLIN - 1, NON_EXPIRED_QUALITY - 1);
                Item actualItem = app.items[0];
                assertItem(expectedItem, actualItem);
            }
        }

        @Nested
        class ExpiredItem {
            @Test
            public void testUpdateQualityForExpiredDefaultItemWhereQualityDecreasesByTwo() {
                GildedRose app = getGildedRoseWithOneItem("DEFAULT_ITEM", EXPIRED_SELLIN, EXPIRED_QUALITY);
                app.updateQuality();
                Item expectedItem = getItem("DEFAULT_ITEM", EXPIRED_SELLIN - 1, EXPIRED_QUALITY - 2);
                Item actualItem = app.items[0];
                assertItem(expectedItem, actualItem);
            }
        }

    }
}