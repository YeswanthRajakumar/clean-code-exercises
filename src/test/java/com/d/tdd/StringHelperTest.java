package com.d.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringHelperTest {
    @Test
    void shouldReturnTrueWhenFirstTwoAndLastTwoLettersAreSame() {
        StringHelper stringHelper = new StringHelper();
        assertFalse(stringHelper.checkEqualityForFirstTwoAndLastTwoChars(""));
        assertFalse(stringHelper.checkEqualityForFirstTwoAndLastTwoChars("A"));
        assertTrue(stringHelper.checkEqualityForFirstTwoAndLastTwoChars("AB"));
        assertFalse(stringHelper.checkEqualityForFirstTwoAndLastTwoChars("ABC"));
        assertTrue(stringHelper.checkEqualityForFirstTwoAndLastTwoChars("AAA"));
        assertTrue(stringHelper.checkEqualityForFirstTwoAndLastTwoChars("ABCAB"));
        assertFalse(stringHelper.checkEqualityForFirstTwoAndLastTwoChars("ABCDEBA"));
    }

    @Test
    void whenGivenAString_shouldTruncateCharacter_A_inFirstTwoPositions() {
        StringHelper stringHelper = new StringHelper();
        assertEquals("", stringHelper.truncateAInFirstTwoPositions(""));
        assertEquals("BCD", stringHelper.truncateAInFirstTwoPositions("ABCD"));
        assertEquals("CD", stringHelper.truncateAInFirstTwoPositions("AACD"));
        assertEquals("BCD", stringHelper.truncateAInFirstTwoPositions("BACD"));
        assertEquals("AA", stringHelper.truncateAInFirstTwoPositions("AAAA"));
        assertEquals("MNAA", stringHelper.truncateAInFirstTwoPositions("MNAA"));
    }
}
