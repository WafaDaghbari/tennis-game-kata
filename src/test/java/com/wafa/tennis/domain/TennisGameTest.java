package com.wafa.tennis.domain;

import com.wafa.tennis.FakeScorePrinter;
import org.junit.jupiter.api.Test;

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
}
