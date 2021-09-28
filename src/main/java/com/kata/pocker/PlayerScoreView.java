package com.kata.pocker;

record PlayerScoreView(PlayerScore playerScore) {

    String toView() {
        if (playerScore.rank() instanceof Rank.TieHighCard) {
            return "Tie.";
        }
        return new StringBuilder(playerScore.name())
                .append(" wins.")
                .append(" - with ")
                .append(toRankView(playerScore.rank()))
                .toString();
    }

    private String toRankView(Rank rank) {
        if (rank instanceof Rank.WinnerHighCard highCard) {
            return "high card: %s".formatted(toView(highCard.getCardValue()));
        }
        if (rank instanceof Rank.LoserHighCard highCard) {
            return "high card: %s".formatted(toView(highCard.getCardValue()));
        }
        if (rank instanceof Rank.RoyalFlush) {
            return "royal flush";
        }
        if (rank instanceof Rank.Straight) {
            return "straight";
        }
        if (rank instanceof Rank.OnePair) {
            return "pair";
        }
        if (rank instanceof Rank.TwoPair) {
            return "two pair";
        }
        if (rank instanceof Rank.FullHouse) {
            return "full house";
        }
        if (rank instanceof Rank.FourOfKing) {
            return "four of a king";
        }
        if (rank instanceof Rank.StraightFlush) {
            return "straight flush";
        }
        if (rank instanceof Rank.Flush) {
            return "flush";
        }
        if (rank instanceof Rank.ThreeOfKind) {
            return "three of a king";
        }
        return "";
    }

    private String toView(CardValue cardValue) {
        return switch (cardValue.value()) {
            case "T" -> "10";
            case "A" -> "Ace";
            case "K" -> "King";
            case "Q" -> "Queen";
            case "J" -> "Jack";
            default -> cardValue.value();
        };
    }

}
