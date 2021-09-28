package com.kata.pocker;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toCollection;

sealed abstract class Rank permits Rank.Flush, Rank.FourOfKing, Rank.FullHouse, Rank.HighCard, Rank.LoserHighCard, Rank.None, Rank.Pair, Rank.RoyalFlush, Rank.Straight, Rank.StraightFlush, Rank.ThreeOfKind, Rank.TieHighCard, Rank.TwoPair, Rank.WinnerHighCard
{
    static final None NONE = new None();

    private final int priority;

    Rank(int priority) {
        this.priority = priority;
    }

    final int priority() {
        return priority;
    }

    static final class RoyalFlush extends Rank {
        RoyalFlush() {
            super(100);
        }
    }

    static final class StraightFlush extends Rank {
        StraightFlush() {
            super(90);
        }
    }

    static final class FourOfKing extends Rank {
        FourOfKing() {
            super(80);
        }
    }

    static final class FullHouse extends Rank {
        FullHouse() {
            super(70);
        }
    }

    static final class Flush extends Rank {
        Flush() {
            super(60);
        }
    }

    static final class Straight extends Rank {
        Straight() {
            super(50);
        }
    }

    static final class ThreeOfKind extends Rank {
        ThreeOfKind() {
            super(40);
        }
    }

    static final class TwoPair extends Rank {
        TwoPair() {
            super(30);
        }
    }

    static final class Pair extends Rank {
        Pair() {
            super(20);
        }
    }

    static final class HighCard extends Rank {
        private final List<CardValue> cardValues;

        HighCard(Set<CardValue> cardValues) {
            super(-1);
            this.cardValues = cardValues.stream()
                    .sorted(comparing(CardValue::priority).reversed())
                    .collect(toCollection(LinkedList::new));
        }

        Rank winner(HighCard that) {
            for (int i = 0; i < cardValues.size(); i++) {
                var cardValue1 = cardValues.get(i);
                var cardValue2 = that.cardValues.get(i);
                if (cardValue1.priority() != cardValue2.priority()) {
                    return winnerCard(cardValue1, cardValue2);
                }
            }
            return new TieHighCard();
        }

        private static Rank winnerCard(CardValue cardValue1, CardValue cardValue2) {
            if (cardValue1.priority() > cardValue2.priority() ) {
                return new WinnerHighCard(cardValue1);
            } else if (cardValue1.priority() < cardValue2.priority()) {
                return new LoserHighCard(cardValue2);
            }
            return new TieHighCard();
        }
    }

    static final class WinnerHighCard extends Rank {
        private CardValue cardValue;

        WinnerHighCard(CardValue cardValue) {
            super(cardValue.cardScore());
            this.cardValue = cardValue;
        }

        public CardValue getCardValue() {
            return cardValue;
        }
    }

    static final class LoserHighCard extends Rank {
        private CardValue cardValue;

        LoserHighCard(CardValue cardValue) {
            super(cardValue.cardScore());
            this.cardValue = cardValue;
        }

        public CardValue getCardValue() {
            return cardValue;
        }
    }

    static final class TieHighCard extends Rank {
        TieHighCard() {
            super(-1);
        }
    }

    static final class None extends Rank {
        None() {
            super(-1);
        }
    }
}