package com.d.tdd;

public class Game {
    private static final int FRAMES = 10;
    private static final int SPARE = 10;
    private static final int STRIKE = 10;
    public int score = 0;
    public int[] pinsList = new int[21];
    int rollCount = 0;

    public void roll(int pinsKnockedDown) {
        pinsList[rollCount] = pinsKnockedDown;
        rollCount += 1;
    }

    public int score() {
        int totalScore = 0;
        int indexToRoll = 0;
        for (int frame = 0; frame < FRAMES; frame++) {
            if (isaStrike(indexToRoll)) {
                int bonusForStrike = pinsList[indexToRoll + 1] + pinsList[indexToRoll + 2];
                totalScore += STRIKE + bonusForStrike;
                indexToRoll += 1;
            } else if (isaSpare(indexToRoll)) {
                totalScore += SPARE + pinsList[indexToRoll + 2];
                indexToRoll += 2;
            } else {
                int currentFrameScore = pinsList[indexToRoll] + pinsList[indexToRoll + 1];
                totalScore += currentFrameScore;
                indexToRoll += 2;
            }
        }
        return totalScore;
    }

    private boolean isaStrike(int indexToRoll) {
        return pinsList[indexToRoll] == STRIKE;
    }

    private boolean isaSpare(int indexToRoll) {
        return pinsList[indexToRoll] + pinsList[indexToRoll + 1] == SPARE;
    }

}