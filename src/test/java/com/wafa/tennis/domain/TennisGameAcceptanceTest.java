package com.wafa.tennis.domain;

import com.wafa.tennis.FakeScorePrinter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class TennisGameAcceptanceTest {
    @Test
    void should_print_scores_and_winner_for_standard_game_sequence() {
        //Given
        var printer = new FakeScorePrinter();
        var tennisGame = new TennisGame(printer);

        //When
        tennisGame.play("ABABAA");

        //Then
        then(printer.outputs())
                .containsExactly(
                        "Player A : 15 / Player B : 0",
                        "Player A : 15 / Player B : 15",
                        "Player A : 30 / Player B : 15",
                        "Player A : 30 / Player B : 30",
                        "Player A : 40 / Player B : 30",
                        "Player A wins the game"
                );
    }
}
