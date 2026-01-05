package com.wafa.tennis.domain;

import com.wafa.tennis.domain.port.ScorePrinter;

public final class TennisGame {

    private final ScorePrinter scorePrinter;

    private int pointsA;
    private int pointsB;
    private boolean gameWon;

    public TennisGame(final ScorePrinter scorePrinter) {
        this.scorePrinter = scorePrinter;
    }

    public void play(final String sequence) {

        for (final char ball : sequence.toCharArray()) {
            if (gameWon) {
                break;
            }

            switch (ball) {
                case 'A' -> pointsA++;
                case 'B' -> pointsB++;
                default -> throw new IllegalArgumentException("Invalid input: " + ball);
            }
            printScoreOrWinner();
        }
    }

    private void printScoreOrWinner() {
        if (hasWinner()) {
            scorePrinter.print(winnerMessage());
            gameWon = true;
            return;
        }

        if (isDeuce()) {
            scorePrinter.print("Deuce");
            return;
        }

        if (isAdvantageA()) {
            scorePrinter.print("Advantage Player A");
            return;
        }

        if (isAdvantageB()) {
            scorePrinter.print("Advantage Player B");
            return;
        }

        scorePrinter.print(formatStandardScore());
    }

    private boolean hasWinner() {
        return (pointsA >= 4 || pointsB >= 4) && Math.abs(pointsA - pointsB) >= 2;
    }

    private String winnerMessage() {
        return pointsA > pointsB
                ? "Player A wins the game"
                : "Player B wins the game";
    }

    private boolean isDeuce() {
        return pointsA >= 3 && pointsB >= 3 && pointsA == pointsB;
    }

    private boolean isAdvantageA() {
        return pointsA >= 3 && pointsB >= 3 && pointsA == pointsB + 1;
    }

    private boolean isAdvantageB() {
        return pointsA >= 3 && pointsB >= 3 && pointsB == pointsA + 1;
    }

    private String formatStandardScore() {
        return "Player A : " + toTennisScore(pointsA)
                + " / Player B : " + toTennisScore(pointsB);
    }

    private int toTennisScore(final int points) {
        return switch (points) {
            case 0 -> 0;
            case 1 -> 15;
            case 2 -> 30;
            default -> 40;
        };
    }
}
