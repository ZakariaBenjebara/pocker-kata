package com.kata.pocker;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

final class Hand {
    private final Set<Card> cards;
    private final List<HandScoring> handScoringList;

    Hand(Set<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Invalid cards list input");
        }
        this.cards = cards.stream()
                .sorted(Card.COMPARATOR)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        this.handScoringList = initHandScoringList();
    }

    Rank doScore() {
        for (var handScoring : handScoringList) {
            var handStats = HandStats.initialize();
            for (Card card : cards) {
                handStats = handStats.analyse(card);
            }
            var score = handScoring.doScoring(handStats);
            if (score != Rank.NONE) {
                return score;
            }
        }
        return Rank.NONE;
    }

    private static List<HandScoring> initHandScoringList() {
        var handScoring = new LinkedList<HandScoring>();
        handScoring.add(new HandScoring.RoyalFlush());
        handScoring.add(new HandScoring.StraightFlush());
        handScoring.add(new HandScoring.FourOfKind());
        handScoring.add(new HandScoring.FullHouse());
        handScoring.add(new HandScoring.Flush());
        handScoring.add(new HandScoring.Straight());
        handScoring.add(new HandScoring.ThreeOfKind());
        handScoring.add(new HandScoring.TwoPair());
        handScoring.add(new HandScoring.OnePair());
        handScoring.add(new HandScoring.Highest());
        return unmodifiableList(handScoring);
    }

}
