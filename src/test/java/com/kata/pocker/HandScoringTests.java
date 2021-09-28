package com.kata.pocker;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandScoringTest {

    @Test
    void shouldScoreRoyalFlush() {
        var cards = Set.of(
                new Card("J", CardSuit.CLUB),
                new Card("Q", CardSuit.CLUB),
                new Card("K", CardSuit.CLUB),
                new Card("A", CardSuit.CLUB),
                new Card("10", CardSuit.CLUB)
        );
        Rank rank = new Hand(cards).doScore();
        assertTrue(rank instanceof Rank.RoyalFlush);
        assertEquals(100, rank.priority());
    }

    @Test
    void shouldScoreStraightFlush() {
        var cards = Set.of(
                new Card("8", CardSuit.HEAT),
                new Card("7", CardSuit.HEAT),
                new Card("6", CardSuit.HEAT),
                new Card("5", CardSuit.HEAT),
                new Card("4", CardSuit.HEAT)
        );
        Rank rank = new Hand(cards).doScore();
        assertTrue(rank instanceof Rank.StraightFlush);
        assertEquals(90, rank.priority());
    }

    @Test
    void shouldScoreFourKingFlush() {
        var cards = Set.of(
                new Card("J", CardSuit.HEAT),
                new Card("J", CardSuit.DIAMOND),
                new Card("J", CardSuit.CLUB),
                new Card("J", CardSuit.SPADE),
                new Card("7", CardSuit.HEAT)
        );
        Rank rank = new Hand(cards).doScore();
        assertTrue(rank instanceof Rank.FourOfKing);
        assertEquals(80, rank.priority());
    }

    @Test
    void shouldScoreFullHouse() {
        var cards = Set.of(
                new Card("10", CardSuit.HEAT),
                new Card("10", CardSuit.DIAMOND),
                new Card("10", CardSuit.CLUB),
                new Card("9", CardSuit.SPADE),
                new Card("9", CardSuit.HEAT)
        );
        Rank rank = new Hand(cards).doScore();
        assertTrue(rank instanceof Rank.FullHouse);
        assertEquals(70, rank.priority());
    }

    @Test
    void shouldScoreFlush() {
        var cards = Set.of(
                new Card("4", CardSuit.DIAMOND),
                new Card("J", CardSuit.DIAMOND),
                new Card("8", CardSuit.DIAMOND),
                new Card("2", CardSuit.DIAMOND),
                new Card("9", CardSuit.DIAMOND)
        );
        Rank rank = new Hand(cards).doScore();
        assertTrue(rank instanceof Rank.Flush);
        assertEquals(60, rank.priority());
    }

    @Test
    void shouldScoreStraight() {
        var cards = Set.of(
                new Card("9", CardSuit.DIAMOND),
                new Card("8", CardSuit.HEAT),
                new Card("7", CardSuit.CLUB),
                new Card("6", CardSuit.SPADE),
                new Card("5", CardSuit.DIAMOND)
        );
        Rank rank = new Hand(cards).doScore();
        assertTrue(rank instanceof Rank.Straight);
        assertEquals(50, rank.priority());
    }

    @Test
    void shouldScoreThreeOfKind() {
        var cards = Set.of(
                new Card("7", CardSuit.DIAMOND),
                new Card("7", CardSuit.HEAT),
                new Card("7", CardSuit.CLUB),
                new Card("K", CardSuit.SPADE),
                new Card("3", CardSuit.DIAMOND)
        );
        Rank rank = new Hand(cards).doScore();
        assertTrue(rank instanceof Rank.ThreeOfKind);
        assertEquals(40, rank.priority());
    }

    @Test
    void shouldScoreTwoPair() {
        var cards = Set.of(
                new Card("4", CardSuit.DIAMOND),
                new Card("4", CardSuit.HEAT),
                new Card("3", CardSuit.CLUB),
                new Card("3", CardSuit.SPADE),
                new Card("A", CardSuit.DIAMOND)
        );
        Rank rank = new Hand(cards).doScore();
        assertTrue(rank instanceof Rank.TwoPair);
        assertEquals(30, rank.priority());
    }

    @Test
    void shouldScorePair() {
        var cards = Set.of(
                new Card("A", CardSuit.DIAMOND),
                new Card("A", CardSuit.HEAT),
                new Card("8", CardSuit.CLUB),
                new Card("4", CardSuit.SPADE),
                new Card("7", CardSuit.DIAMOND)
        );
        Rank rank = new Hand(cards).doScore();
        assertTrue(rank instanceof Rank.Pair);
        assertEquals(20, rank.priority());
    }

    @Test
    void shouldScoreHighestCard() {
        var cards = Set.of(
                new Card("3", CardSuit.DIAMOND),
                new Card("J", CardSuit.HEAT),
                new Card("8", CardSuit.CLUB),
                new Card("4", CardSuit.SPADE),
                new Card("2", CardSuit.DIAMOND)
        );
        Rank rank = new Hand(cards).doScore();
        assertTrue(rank instanceof Rank.HighCard);
        Rank.HighCard highCardRank = (Rank.HighCard) rank;
        assertEquals(10, highCardRank.priority());
        assertEquals("J", highCardRank.getCard().value());
    }
}