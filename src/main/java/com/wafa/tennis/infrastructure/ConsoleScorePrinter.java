package com.wafa.tennis.infrastructure;

import com.wafa.tennis.domain.port.ScorePrinter;

public final class ConsoleScorePrinter implements ScorePrinter {
    @Override
    public void print(final String message) {
        System.out.println(message);
    }
}
