package com.c.refactoring.movie;

import java.util.Arrays;
import java.util.List;

import static com.c.refactoring.StringUtils.isNumeric;

public class Movie {
    public static final int VALID_LENGTH_FOR_A_RATING = 3;
    private static final List<String> VALID_B_RATINGS = Arrays.asList("B1", "B2", "B3", "B4");
    String rating;

    public Movie(String rating) {
        this.rating = rating;
    }

    public boolean isValidRating() {
        if (rating == null) return false;
        if (isValidARating()) return true;
        return isValidBRating();
    }

    private boolean isValidARating() {
        char firstCharacterOfRating = rating.charAt(0);
        return firstCharacterOfRating == 'A' && rating.length() == VALID_LENGTH_FOR_A_RATING && isNumeric(rating.substring(1, 3));
    }

    private boolean isValidBRating() {
        return VALID_B_RATINGS.contains(rating);
    }

}
