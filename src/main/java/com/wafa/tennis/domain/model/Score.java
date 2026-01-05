package com.wafa.tennis.domain.model;

public record Score(int pointsA, int pointsB) {

    private static final String DEUCE = "Deuce";
    private static final String ADVANTAGE_A = "Advantage Player A";
    private static final String ADVANTAGE_B = "Advantage Player B";
    private static final String WIN_A = "Player A wins the game";
    private static final String WIN_B = "Player B wins the game";

    private static final int FORTY_THRESHOLD = 3;
    private static final int MIN_POINTS_TO_WIN = 4;
    private static final int MIN_DIFFERENCE_TO_WIN = 2;

    public static Score initial() {
        return new Score(0, 0);
    }

    public Score pointFor(final Player player) {
        return switch (player) {
            case A -> new Score(pointsA + 1, pointsB);
            case B -> new Score(pointsA, pointsB + 1);
        };
    }

    public boolean hasWinner() {
        return (pointsA >= MIN_POINTS_TO_WIN || pointsB >= MIN_POINTS_TO_WIN)
                && Math.abs(pointsA - pointsB) >= MIN_DIFFERENCE_TO_WIN;
    }

    public Player winner() {
        if (!hasWinner()) {
            throw new IllegalStateException("No winner yet");
        }
        return pointsA > pointsB ? Player.A : Player.B;
    }

    public String winnerMessage() {
        return winner() == Player.A ? WIN_A : WIN_B;
    }

    public String display() {
        if (isDeuce()) {
            return DEUCE;
        }
        if (isAdvantage(Player.A)) {
            return ADVANTAGE_A;
        }
        if (isAdvantage(Player.B)) {
            return ADVANTAGE_B;
        }
        return "Player A : " + toTennisScore(pointsA)
                + " / Player B : " + toTennisScore(pointsB);
    }

    private boolean isDeuce() {
        return pointsA >= FORTY_THRESHOLD
                && pointsB >= FORTY_THRESHOLD
                && pointsA == pointsB;
    }

    private boolean isAdvantage(final Player player) {
        if (pointsA < FORTY_THRESHOLD
                || pointsB < FORTY_THRESHOLD) {
            return false;
        }

        return switch (player) {
            case A -> pointsA == pointsB + 1;
            case B -> pointsB == pointsA + 1;
        };
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
