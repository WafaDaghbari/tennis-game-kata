package com.wafa.tennis.domain;

import com.wafa.tennis.FakeScorePrinter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

class TennisGameTest {

    @Test
    void should_throw_exception_when_sequence_contains_invalid_character() {
        // Given
        var printer = new FakeScorePrinter();
        var tennisGame = new TennisGame(printer);

        // When / Then
        thenThrownBy(() -> tennisGame.play("ABX"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid input");
    }

    @Test
    void should_print_deuce_when_both_players_reach_40() {
        // Given
        var printer = new FakeScorePrinter();
        var tennisGame = new TennisGame(printer);

        // When
        tennisGame.play("ABABAB");

        // Then
        then(printer.outputs())
                .containsExactly(
                        "Player A : 15 / Player B : 0",
                        "Player A : 15 / Player B : 15",
                        "Player A : 30 / Player B : 15",
                        "Player A : 30 / Player B : 30",
                        "Player A : 40 / Player B : 30",
                        "Deuce"
                );
    }

    @Test
    void should_display_standard_scores_for_equalities_before_40() {
        var printer = new FakeScorePrinter();
        var tennisGame = new TennisGame(printer);
        tennisGame.play("ABAB");

        then(printer.outputs())
                .last()
                .isEqualTo("Player A : 30 / Player B : 30");

        then(printer.outputs())
                .doesNotContain("Deuce");
    }
}
