package com.wafa.tennis.domain;

import com.wafa.tennis.domain.model.Player;
import com.wafa.tennis.domain.model.Score;
import com.wafa.tennis.domain.port.ScorePrinter;

public final class TennisGame {

    private final ScorePrinter scorePrinter;

    public TennisGame(final ScorePrinter scorePrinter) {
        this.scorePrinter = scorePrinter;
    }

    public void play(final String sequence) {
        Score score = Score.initial();

        for (char ball : sequence.toCharArray()) {
            Player player = Player.from(ball);
            score = score.pointFor(player);

            if (score.hasWinner()) {
                scorePrinter.print(score.winnerMessage());
                return;
            }

            scorePrinter.print(score.display());
        }
    }
}
