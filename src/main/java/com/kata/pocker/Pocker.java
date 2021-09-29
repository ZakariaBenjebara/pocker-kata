package com.kata.pocker;

import java.util.Arrays;

import static java.util.stream.Collectors.toSet;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

final class Pocker {
    private Pocker() {}

    static PockerHandRankBuilder handRanks() {
        return new PockerHandRankBuilder();
    }

    static class PockerHandRankBuilder {
        private String playerName;
        private Player player1;
        private Player player2;

        PockerHandRankBuilder player(String name) {
            if (isAlreadyUsed(name)) {
                throw new IllegalArgumentException("Invalid player name, this name is already exist");
            }
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

        private boolean isAlreadyUsed(String name) {
            return isNotBlank(playerName) && playerName.equals(name);
        }

        SetScore score() {
            var playerScore1 = player1.score();
            var playerScore2 = player2.score();
            return playerScore1.whoWins(playerScore2);
        }
    }
}
