package com.kata.pocker;

final class PlayerScoreView {
    private final PlayerScore playerScore;

    PlayerScoreView(PlayerScore playerScore) {
        this.playerScore = playerScore;
    }

    String toRepresentation() {
        if (playerScore.rank() instanceof Rank.TieHighCard) {
            return "Tie.";
        }
        return new StringBuilder(playerScore.name())
                .append(" wins.")
                .append(" - with ")
                .append(toRankRepresentation(playerScore.rank()))
                .toString();
    }

    private String toRankRepresentation(Rank rank) {
        if (rank instanceof Rank.WinnerHighCard highCard) {
            return "high card: %s".formatted(toRepresentation(highCard.getCardValue()));
        }
        if (rank instanceof Rank.LoserHighCard highCard) {
            return "high card: %s".formatted(toRepresentation(highCard.getCardValue()));
        }
        if (rank instanceof Rank.RoyalFlush) {
            return "royal flush";
        }
        if (rank instanceof Rank.Straight) {
            return "straight";
        }
        if (rank instanceof Rank.Pair) {
            return "pair";
        }
        if (rank instanceof Rank.TwoPair) {
            return "two pair";
        }
        if (rank instanceof Rank.FullHouse) {
            return "full house";
        }
        if (rank instanceof Rank.FourOfKing) {
            return "four of king";
        }
        if (rank instanceof Rank.StraightFlush) {
            return "straight flush";
        }
        if (rank instanceof Rank.Flush) {
            return "flush";
        }
        if (rank instanceof Rank.ThreeOfKind) {
            return "three of king";
        }
        return "";
    }

    private String toRepresentation(CardValue cardValue) {
        return switch (cardValue.value()) {
            case "A" -> "Ace";
            case "K" -> "King";
            case "Q" -> "Queen";
            case "J" -> "Jack";
            default -> cardValue.value();
        };
    }

}
