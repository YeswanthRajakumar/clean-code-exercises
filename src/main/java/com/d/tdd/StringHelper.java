package com.d.tdd;

public class StringHelper {
    public String truncateAInFirstTwoPositions(String string) {
        return isLessThanTwoCharacters(string) ? "" : truncateCharacterA(string);
    }

    private String truncateCharacterA(String string) {
        String firstTwoCharacters = string.substring(0, 2);
        String restOfTheCharacters = string.substring(2);

        return firstTwoCharacters.replaceAll("A", "") + restOfTheCharacters;
    }

    public boolean checkEqualityForFirstTwoAndLastTwoChars(String inputString) {
        if (isLessThanTwoCharacters(inputString))
            return false;

        String firstTwoChars = inputString.substring(0, 2);
        String lastTwoChars = inputString.substring(inputString.length() - 2);

        return firstTwoChars.equals(lastTwoChars);
    }

    private boolean isLessThanTwoCharacters(String inputString) {
        return inputString.length() < 2;
    }
}
