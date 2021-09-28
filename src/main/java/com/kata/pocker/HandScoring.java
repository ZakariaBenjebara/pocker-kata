package com.kata.pocker;

sealed abstract class HandScoring {

    abstract Rank doScoring(HandStats handStats);

    static final class RoyalFlush extends HandScoring {
        @Override
        Rank doScoring(HandStats handStats) {
            if (handStats.score == 50 && handStats.hasSameSuite) {
                return new Rank.RoyalFlush();
            }
            return Rank.NONE;
        }
    }

    static final class StraightFlush extends HandScoring {
        @Override
        Rank doScoring(HandStats handStats) {
            if (handStats.score < 50 && handStats.hasSequentialSuite && handStats.hasSameSuite) {
                return new Rank.StraightFlush();
            }
            return Rank.NONE;
        }
    }

    static final class FourOfKind extends HandScoring {
        @Override
        Rank doScoring(HandStats handStats) {
            if (handStats.cardValueCounts.containsValue(4)) {
                return new Rank.FourOfKing();
            }
            return Rank.NONE;
        }
    }

    static final class FullHouse extends HandScoring {
        @Override
        Rank doScoring(HandStats handStats) {
            if (handStats.cardValueCounts.containsValue(3)
                    && handStats.cardValueCounts.containsValue(2)) {
                return new Rank.FullHouse();
            }
            return Rank.NONE;
        }
    }

    static final class Flush extends HandScoring {
        @Override
        Rank doScoring(HandStats handStats) {
            if (handStats.hasSameSuite && !handStats.hasSequentialSuite) {
                return new Rank.Flush();
            }
            return Rank.NONE;
        }
    }

    static final class Straight extends HandScoring {
        @Override
        Rank doScoring(HandStats handStats) {
            if (!handStats.hasSameSuite && handStats.hasSequentialSuite) {
                return new Rank.Straight();
            }
            return Rank.NONE;
        }
    }

    static final class ThreeOfKind extends HandScoring {
        @Override
        Rank doScoring(HandStats handStats) {
            if (handStats.cardValueCounts.containsValue(3) && !handStats.cardValueCounts.containsValue(2)) {
                return new Rank.ThreeOfKind();
            }
            return Rank.NONE;
        }
    }

    static final class TwoPair extends HandScoring {
        @Override
        Rank doScoring(HandStats handStats) {
            long pair = handStats.cardValueCounts
                    .values().stream()
                    .filter(count -> count == 2).count();
            if (pair == 2) {
                return new Rank.TwoPair();
            }
            return Rank.NONE;
        }
    }

    static final class Pair extends HandScoring {
        @Override
        Rank doScoring(HandStats handStats) {
            long pairCount = handStats.cardValueCounts
                    .values().stream()
                    .filter(count -> count == 2).count();
            if (pairCount == 1) {
                return new Rank.Pair();
            }
            return Rank.NONE;
        }
    }

    static final class Highest extends HandScoring {
        @Override
        Rank doScoring(HandStats handStats) {
            return new Rank.HighCard(handStats.highestCardValue);
        }
    }
}
