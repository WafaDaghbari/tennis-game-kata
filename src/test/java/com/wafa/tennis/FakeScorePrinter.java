package com.wafa.tennis;

import com.wafa.tennis.domain.port.ScorePrinter;

import java.util.ArrayList;
import java.util.List;

public class FakeScorePrinter implements ScorePrinter {

    private final List<String> outputs = new ArrayList<>();

    @Override
    public void print(final String message) {
        outputs.add(message);
    }

    public List<String> outputs() {
        return outputs;
    }
}
