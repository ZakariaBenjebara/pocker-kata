package com.kata.pocker;

record PlayerScore(PlayerName name, Rank rank) {

    SetScore whoWins(PlayerScore opponent) {
        if (shouldCalculateHighCardScore(opponent)) {
            return calculateHighCardScoreWinner(opponent);
        } else {
            return calculateTotalScore(opponent);
        }
    }

    public String nameValue() {
        return name.value();
    }

    private SetScore calculateTotalScore(PlayerScore opponent) {
        if (score() > opponent.score()) {
            return new SetScore(this, opponent);
        } else if (score() < opponent.score()) {
            return new SetScore(opponent, this);
        }
        return SetScore.tie(this.name(), opponent.name());
    }

    private SetScore calculateHighCardScoreWinner(PlayerScore opponent) {
        var thisRank = (Rank.HighCard) this.rank;
        var opponentRank = (Rank.HighCard) opponent.rank;
        var result = thisRank.whoWins(opponentRank);
        if (result instanceof Rank.WinnerHighCard) {
            return new SetScore(new PlayerScore(this.name, result), opponent);
        } else if (result instanceof Rank.LoserHighCard) {
            return new SetScore(new PlayerScore(opponent.name, result), this);
        }
        return SetScore.tie(this.name(), opponent.name());
    }

    private boolean shouldCalculateHighCardScore(PlayerScore opponent) {
        return rank instanceof Rank.HighCard && opponent.rank instanceof Rank.HighCard;
    }

    private int score() {
        return rank.priority();
    }
}
