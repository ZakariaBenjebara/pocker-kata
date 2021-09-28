package com.kata.pocker;

sealed abstract class Rank permits Rank.Flush,
                                    Rank.FourOfKing,
                                    Rank.FullHouse,
        Rank.HighCard,
                                    Rank.None,
                                    Rank.Pair,
                                    Rank.RoyalFlush,
                                    Rank.Straight,
                                    Rank.StraightFlush,
                                    Rank.ThreeOfKind,
                                    Rank.TwoPair
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
        private final CardValue card;

        HighCard(CardValue card) {
            super(card.priority());
            this.card = card;
        }

        public CardValue getCard() {
            return card;
        }
    }

    static final class None extends Rank {
        None() {
            super(-1);
        }

    }
}