package com.kata.pocker;

record SetScore(PlayerScore winner, PlayerScore loser) {

    static SetScore tie(PlayerName playerName1, PlayerName playerName2) {
        return new SetScore(new PlayerScore(playerName1, new Rank.TieHighCard()),
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
