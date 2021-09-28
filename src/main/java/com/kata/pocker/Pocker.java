package com.kata.pocker;

import java.util.Arrays;

import static java.util.stream.Collectors.toSet;

final class Pocker {
    static PockerHandRankBuilder handRanks() {
        return new PockerHandRankBuilder();
    }

    static class PockerHandRankBuilder {
        private String playerName;
        private Player player1;
        private Player player2;

        PockerHandRankBuilder player(String name) {
            this.playerName = name;
            return this;
        }

        PockerHandRankBuilder hands(String... hands) {
            var cards = Arrays.stream(hands).map(Card::from).collect(toSet());
            if (player1 == null) {
                player1 = new Player(playerName, new Hand(cards));
                return this;
            }
            player2 = new Player(playerName, new Hand(cards));
            return this;
        }

        SetScore score() {
            var score1 = player1.score();
            var score2 = player2.score();
            return new SetScore(score1, score2);
        }
    }
}
