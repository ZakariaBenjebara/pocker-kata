package com.kata.pocker;

final class SetScore {
    private final PlayerScore playerScore1;
    private final PlayerScore playerScore2;

    SetScore(PlayerScore playerScore1, PlayerScore playerScore2) {
        this.playerScore1 = playerScore1;
        this.playerScore2 = playerScore2;
    }

    String report() {
        var result = playerScore1.whoWins(playerScore2);
        return Report.winner(result).representation();
    }

    private record Report(String representation) {
        static Report winner(PlayerScore playerScore) {
            return new Report(new PlayerScoreView(playerScore).toRepresentation());
        }
    }
}
