package com.kata.pocker;

import java.util.HashMap;
import java.util.Map;

final class HandStats {
    int score = 0;
    boolean hasSameSuite = true;
    boolean hasSequentialSuite = true;
    Card previewsCard = null;
    CardValue highestCardValue = CardValue.LOWEST;
    Map<CardValue, Integer> cardValueCounts = new HashMap<>(5);

    void analyse(Card card) {
        if (card.value().priority() > highestCardValue.priority()) {
            highestCardValue = card.value();
        }
        score += card.cardScore();
        cardValueCounts.compute(card.value(), (k, v) -> v == null ? 1 : v + 1);
        if (previewsCard != null) {
            hasSameSuite &= card.hasSameSuit(previewsCard);
            hasSequentialSuite &= card.equalsPreviewsPriority(previewsCard);
        }
        previewsCard = card;
    }
}
