package com.kata.pocker;

record ScoreSet(PlayerScore winner, PlayerScore loser) {

    static ScoreSet tie(PlayerName playerName1, PlayerName playerName2) {
        return new ScoreSet(new PlayerScore(playerName1, new Rank.TieHighCard()),
                new PlayerScore(playerName2, new Rank.TieHighCard()));
    }

    String printWinnerReport() {
        return Report.createReportFor(winner).view();
    }

    private record Report(String view) {
        static Report createReportFor(PlayerScore playerScore) {
            return new Report(new PlayerScoreView(playerScore).toView());
        }
    }
}
