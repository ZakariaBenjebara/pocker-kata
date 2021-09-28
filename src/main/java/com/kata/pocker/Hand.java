package com.kata.pocker;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class Hand {
    private final Set<Card> cards;

    public Hand(Set<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Invalid cards list input");
        }
        this.cards = cards.stream()
                .sorted(Card.COMPARATOR)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

}
