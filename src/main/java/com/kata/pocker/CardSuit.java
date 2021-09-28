package com.kata.pocker;

import static java.util.Arrays.stream;

enum CardSuit {
    CLUB("C"), DIAMOND("D"), HEAT("H"), SPADE("S");

    private final String name;

    CardSuit(String name) {
        this.name = name;
    }

    static CardSuit from(String representation) {
        return stream(values())
                .filter(v -> v.name.equals(representation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid card suit value " + representation));
    }
}
