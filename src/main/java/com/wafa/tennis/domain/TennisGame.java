package com.wafa.tennis.domain;

import com.wafa.tennis.domain.port.ScorePrinter;

public final class TennisGame {

    private final ScorePrinter scorePrinter;

    private int scoreA;
    private int scoreB;
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
                case 'A' -> handleBallWonByA();
                case 'B' -> handleBallWonByB();
                default -> throw new IllegalArgumentException("Invalid input: " + ball);
            }
        }
    }

    private void handleBallWonByA() {
        if (scoreA == 40 && scoreB < 40) {
            scorePrinter.print("Player A wins the game");
            gameWon = true;
            return;
        }

        scoreA = nextScore(scoreA);
        printCurrentScore();
    }

    private void handleBallWonByB() {
        if (scoreB == 40 && scoreA < 40) {
            scorePrinter.print("Player B wins the game");
            gameWon = true;
            return;
        }

        scoreB = nextScore(scoreB);
        printCurrentScore();
    }

    private void printCurrentScore() {
        scorePrinter.print(formatScore());
    }

    private String formatScore() {
        if (isDeuce()) {
            return "Deuce";
        }
        return "Player A : " + scoreA + " / Player B : " + scoreB;
    }

    private boolean isDeuce() {
        return scoreA == 40 && scoreB == 40;
    }

    private int nextScore(final int currentScore) {
        return switch (currentScore) {
            case 0 -> 15;
            case 15 -> 30;
            case 30 -> 40;
            default -> currentScore;
        };
    }
}
