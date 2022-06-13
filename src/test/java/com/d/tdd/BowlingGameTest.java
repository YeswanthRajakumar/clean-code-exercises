package com.d.tdd;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingGameTest {
    Game game = new Game();

    private void rollMultipleTimes(int pinsKnockedDown, int noOfTimesToRoll) {
        for (int i = 1; i <= noOfTimesToRoll; i++)
            game.roll(pinsKnockedDown);
    }

    @Nested
    class TestPointsCalculationWithoutBonus {

        @Test
        void shouldReturn_0_WhenNoPinsKnockedDownOn_20_Rolls() {
            rollMultipleTimes(0, 20);
            int[] expectedPinsList = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            assertEquals(0, game.score());
            assertArrayEquals(expectedPinsList, game.pinsList);
        }

        @Test
        void shouldReturn_20_When_1_PinKnockedDownOnEachRollOn_20_Rolls() {
            rollMultipleTimes(1, 20);
            int[] expectedPinsList = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0};
            assertEquals(20, game.score());
            assertArrayEquals(expectedPinsList, game.pinsList);
        }

    }

    @Nested
    class TestPointsCalculationWithBonus {
        @Nested
        class SpareTest {
            @Test
            void test_1_SpareIn_20_Rolls() {
                rollMultipleTimes(5, 2);
                rollMultipleTimes(1, 18);
                int[] expectedPinsList = new int[]{5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0};
                assertEquals(29, game.score());
                assertArrayEquals(expectedPinsList, game.pinsList);
            }

        }

        @Nested
        class StrikeTest {
            @Test
            void test_1_StrikeIn_20_Rolls() {
                rollMultipleTimes(10, 1);
                rollMultipleTimes(1, 19);
                int[] expectedPinsList = new int[]{10, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0};
                assertEquals(30, game.score());
                assertArrayEquals(expectedPinsList, game.pinsList);
            }

        }

    }
}