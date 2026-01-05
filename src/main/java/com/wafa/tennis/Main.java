package com.wafa.tennis;

import com.wafa.tennis.domain.TennisGame;
import com.wafa.tennis.domain.port.ScorePrinter;
import com.wafa.tennis.infrastructure.ConsoleScorePrinter;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java -cp target/classes com.wafa.tennis.Main <sequence>");
            System.err.println("Example: java -cp target/classes com.wafa.tennis.Main AAABBBABAA");
            System.exit(1);
        }

        ScorePrinter printer = new ConsoleScorePrinter();
        TennisGame game = new TennisGame(printer);

        try {
            game.play(args[0]);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
