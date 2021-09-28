package com.kata.pocker;

record PlayerScore(String name, Rank rank) {

    PlayerScore whoWins(PlayerScore opponent) {
        if (rank instanceof Rank.HighCard thisRank && opponent.rank instanceof Rank.HighCard opponentRank) {
            return highCardWinner(opponent, thisRank, opponentRank);
        } else {
            return totalScoreWinner(opponent);
        }
    }

    private PlayerScore totalScoreWinner(PlayerScore opponent) {
        if (score() > opponent.score()) {
            return this;
        } else if (score() < opponent.score()) {
            return opponent;
        }
        return new PlayerScore(this.name, new Rank.TieHighCard());
    }

    private PlayerScore highCardWinner(PlayerScore opponent,
                                       Rank.HighCard thisRank,
                                       Rank.HighCard opponentRank) {
        Rank result = thisRank.winner(opponentRank);
        if (result instanceof Rank.WinnerHighCard) {
            return new PlayerScore(name, result);
        } else if (result instanceof Rank.LoserHighCard) {
            return new PlayerScore(opponent.name, result);
        }
        return new PlayerScore(opponent.name, new Rank.TieHighCard());
    }

    private int score() {
        return rank.priority();
    }
}
