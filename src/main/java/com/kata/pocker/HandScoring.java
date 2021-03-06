package com.kata.pocker;

sealed abstract class HandScoring {
    private static final int DOUBLE_CARD_COUNT = 2;
    private static final int TRIPLE_CARD_COUNT = 3;
    private static final int FOUR_CARD_COUNT = 4;
    private static final int TOTAL_ROYAL_FLUSH_SCORE = 50;

    abstract Rank doScoring(HandStats handStats);

    static final class RoyalFlush extends HandScoring {

        @Override
        Rank doScoring(HandStats handStats) {
            if (handStats.totalScore() == TOTAL_ROYAL_FLUSH_SCORE && handStats.hasSameSuite()) {
                return new Rank.RoyalFlush();
            }
            return Rank.NONE;
        }
    }

    static final class StraightFlush extends HandScoring {
        @Override
        Rank doScoring(HandStats handStats) {
            if (handStats.totalScore() < TOTAL_ROYAL_FLUSH_SCORE
                    && handStats.hasSequentialSuite() && handStats.hasSameSuite()) {
                return new Rank.StraightFlush();
            }
            return Rank.NONE;
        }
    }

    static final class FourOfKind extends HandScoring {

        @Override
        Rank doScoring(HandStats handStats) {
            if (handStats.cardValueCounts().containsValue(FOUR_CARD_COUNT)) {
                return new Rank.FourOfKing();
            }
            return Rank.NONE;
        }
    }

    static final class FullHouse extends HandScoring {

        @Override
        Rank doScoring(HandStats handStats) {
            if (handStats.cardValueCounts().containsValue(TRIPLE_CARD_COUNT)
                    && handStats.cardValueCounts().containsValue(DOUBLE_CARD_COUNT)) {
                return new Rank.FullHouse();
            }
            return Rank.NONE;
        }
    }

    static final class Flush extends HandScoring {
        @Override
        Rank doScoring(HandStats handStats) {
            if (handStats.hasSameSuite() && !handStats.hasSequentialSuite()) {
                return new Rank.Flush();
            }
            return Rank.NONE;
        }
    }

    static final class Straight extends HandScoring {
        @Override
        Rank doScoring(HandStats handStats) {
            if (!handStats.hasSameSuite() && handStats.hasSequentialSuite()) {
                return new Rank.Straight();
            }
            return Rank.NONE;
        }
    }

    static final class ThreeOfKind extends HandScoring {
        @Override
        Rank doScoring(HandStats handStats) {
            if (handStats.cardValueCounts().containsValue(TRIPLE_CARD_COUNT)
                    && !handStats.cardValueCounts().containsValue(DOUBLE_CARD_COUNT)) {
                return new Rank.ThreeOfKind();
            }
            return Rank.NONE;
        }
    }

    static sealed abstract class BasePair extends HandScoring {
        private static final int PAIR_NUMBER = 2;

        @Override
        Rank doScoring(HandStats handStats) {
            long pairCount = handStats.cardValueCounts()
                    .values().stream()
                    .filter(count -> count == PAIR_NUMBER)
                    .count();
            if (pairCount == count()) {
                return rank();
            }
            return Rank.NONE;
        }

        protected abstract Rank rank();

        protected abstract long count();
    }

    static final class TwoPair extends BasePair {
        @Override
        protected Rank rank() {
            return new Rank.TwoPair();
        }

        @Override
        protected long count() {
            return 2;
        }
    }

    static final class OnePair extends BasePair {
        @Override
        protected long count() {
            return 1;
        }

        @Override
        protected Rank rank() {
            return new Rank.OnePair();
        }
    }

    static final class Highest extends HandScoring {
        @Override
        Rank doScoring(HandStats handStats) {
            return new Rank.HighCard(handStats.cardValueCounts().keySet());
        }
    }
}
