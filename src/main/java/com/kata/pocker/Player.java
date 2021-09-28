package com.kata.pocker;

record Player(String name, Hand hand) {
    PlayerScore score() {
        return new PlayerScore(name, hand.doScore());
    }
}
