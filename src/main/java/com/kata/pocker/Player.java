package com.kata.pocker;

record Player(PlayerName name, Hand hand) {

    Player(String name, Hand hand) {
        this(new PlayerName(name), hand);
    }

    PlayerScore score() {
        return new PlayerScore(name, hand.doScore());
    }
}
