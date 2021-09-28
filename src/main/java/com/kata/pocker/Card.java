package com.kata.pocker;

import java.util.Comparator;

import static org.apache.commons.lang3.StringUtils.isBlank;

record Card(CardValue value, CardSuit cardSuit) {
    static final Comparator<Card> COMPARATOR = Comparator.comparing(card -> card.value.priority());

    Card(String value, CardSuit cardSuit) {
        this(new CardValue(value), cardSuit);
    }

    static Card from(String representation) {
        if (isBlank(representation) || representation.length() != 2) {
            throw new IllegalArgumentException("Invalid card input");
        }
        return new Card(new CardValue(String.valueOf(representation.charAt(0))),
                CardSuit.from(String.valueOf(representation.charAt(1))));
    }

    int cardScore() {
        return value.cardScore();
    }

    int priority() {
        return value.priority();
    }

    boolean hasSameSuit(Card card) {
        return this.cardSuit == card.cardSuit;
    }

    boolean equalsPreviewsPriority(Card card) {
        return this.value.priority() == card.value.priority() + 1;
    }

    boolean hasNumericValue() {
        return this.value.hasNumericValue();
    }

}
