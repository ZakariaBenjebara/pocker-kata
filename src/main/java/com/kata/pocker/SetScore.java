package com.kata.pocker;

record SetScore(PlayerScore winner, PlayerScore loser) {

    static SetScore tie(String playerName1, String playerName2) {
        return new SetScore(new PlayerScore(playerName1, new Rank.TieHighCard()),
                new PlayerScore(playerName2, new Rank.TieHighCard()));
    }

    String printWinnerReport() {
        return Report.winner(winner).view();
    }

    private record Report(String view) {
        static Report winner(PlayerScore playerScore) {
            return new Report(new PlayerScoreView(playerScore).toView());
        }
    }
}
