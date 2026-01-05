package com.wafa.tennis.domain.model;

public enum Player {
    A, B;

    public static Player from(final char c) {
        return switch (c) {
            case 'A' -> A;
            case 'B' -> B;
            default -> throw new IllegalArgumentException("Invalid input: " + c);
        };
    }
}
