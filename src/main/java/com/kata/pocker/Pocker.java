package com.kata.pocker;

import java.util.Arrays;

import static java.util.stream.Collectors.toSet;

public final class Pocker {
    public static PockerHandRankBuilder handRanks() {
        return new PockerHandRankBuilder();
    }

    public static class PockerHandRankBuilder {
        private String playerName;
        private Player player1;
        private Player player2;

        public PockerHandRankBuilder player(String name) {
            this.playerName = name;
            return this;
        }

        public PockerHandRankBuilder hands(String... hands) {
            var cards = Arrays.stream(hands).map(Card::from).collect(toSet());
            if (player1 == null) {
                player1 = new Player(playerName, new Hand(cards));
                return this;
            }
            player2 = new Player(playerName, new Hand(cards));
            return this;
        }

        public SetScore score() {
            return null;
        }
    }
}
