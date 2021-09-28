package com.kata.pocker;

import java.util.HashMap;
import java.util.Map;

record HandStats(int totalScore, boolean hasSameSuite, boolean hasSequentialSuite,
                 Card previewsCard,
                 Map<CardValue, Integer> cardValueCounts) {

    static HandStats initialize() {
        return new HandStats(0, true, true, null,
                Map.copyOf(new HashMap<>(5)));
    }

    HandStats analyse(Card card) {
        var totalScore = this.totalScore + card.cardScore();;
        var cardValueCounts = new HashMap<>(this.cardValueCounts);
        var previewsCard = this.previewsCard;
        var hasSameSuite = this.hasSameSuite;
        var hasSequentialSuite = this.hasSequentialSuite;
        cardValueCounts.compute(card.value(), (k, v) -> v == null ? 1 : v + 1);
        if (previewsCard != null) {
            hasSameSuite &= card.hasSameSuit(previewsCard);
            hasSequentialSuite &= card.equalsPreviewsPriority(previewsCard);
        }
        previewsCard = card;
        return new HandStats(totalScore, hasSameSuite, hasSequentialSuite, previewsCard, Map.copyOf(cardValueCounts));
    }
}
