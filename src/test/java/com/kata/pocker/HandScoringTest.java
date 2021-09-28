package com.kata.pocker;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandScoringTest {

    @Test
    void shouldScoreRoyalFlush() {
        HandStats hand = new HandStats();

        var cards = Stream.of(
                new Card("J", CardSuit.CLUB),
                new Card("Q", CardSuit.CLUB),
                new Card("K", CardSuit.CLUB),
                new Card("A", CardSuit.CLUB),
                new Card("10", CardSuit.CLUB)
                ).sorted(Card.COMPARATOR).toList();
        cards.forEach(hand::analyse);
        HandScoring.RoyalFlush royalFlush = new HandScoring.RoyalFlush();
        Rank rank = royalFlush.doScoring(hand);
        assertTrue(rank instanceof Rank.RoyalFlush);
        assertEquals(100, rank.priority());
    }

    @Test
    void shouldScoreStraightFlush() {
        HandStats hand = new HandStats();

        var cards = Stream.of(
                new Card("8", CardSuit.HEAT),
                new Card("7", CardSuit.HEAT),
                new Card("6", CardSuit.HEAT),
                new Card("5", CardSuit.HEAT),
                new Card("4", CardSuit.HEAT)
        ).sorted(Card.COMPARATOR).toList();
        cards.forEach(hand::analyse);
        HandScoring.StraightFlush straightFlush = new HandScoring.StraightFlush();
        Rank rank = straightFlush.doScoring(hand);
        assertTrue(rank instanceof Rank.StraightFlush);
        assertEquals(90, rank.priority());
    }

    @Test
    void shouldScoreFourKingFlush() {
        HandStats hand = new HandStats();

        var cards = Stream.of(
                new Card("J", CardSuit.HEAT),
                new Card("J", CardSuit.DIAMOND),
                new Card("J", CardSuit.CLUB),
                new Card("J", CardSuit.SPADE),
                new Card("7", CardSuit.HEAT)
        ).sorted(Card.COMPARATOR).toList();
        cards.forEach(hand::analyse);
        HandScoring.FourOfKind fourOfKind = new HandScoring.FourOfKind();
        Rank rank = fourOfKind.doScoring(hand);
        assertTrue(rank instanceof Rank.FourOfKing);
        assertEquals(80, rank.priority());
    }

    @Test
    void shouldScoreFullHouse() {
        HandStats hand = new HandStats();

        var cards = Stream.of(
                new Card("10", CardSuit.HEAT),
                new Card("10", CardSuit.DIAMOND),
                new Card("10", CardSuit.CLUB),
                new Card("9", CardSuit.SPADE),
                new Card("9", CardSuit.HEAT)
        ).sorted(Card.COMPARATOR).toList();
        cards.forEach(hand::analyse);
        HandScoring.FullHouse fullHouse = new HandScoring.FullHouse();
        Rank rank = fullHouse.doScoring(hand);
        assertTrue(rank instanceof Rank.FullHouse);
        assertEquals(70, rank.priority());
    }

    @Test
    void shouldScoreFlush() {
        HandStats hand = new HandStats();

        var cards = Stream.of(
                new Card("4", CardSuit.DIAMOND),
                new Card("J", CardSuit.DIAMOND),
                new Card("8", CardSuit.DIAMOND),
                new Card("2", CardSuit.DIAMOND),
                new Card("9", CardSuit.DIAMOND)
        ).sorted(Card.COMPARATOR).toList();
        cards.forEach(hand::analyse);
        HandScoring.Flush flush = new HandScoring.Flush();
        Rank rank = flush.doScoring(hand);
        assertTrue(rank instanceof Rank.Flush);
        assertEquals(60, rank.priority());    }

    @Test
    void shouldScoreStraight() {
        HandStats hand = new HandStats();

        var cards = Stream.of(
                new Card("9", CardSuit.DIAMOND),
                new Card("8", CardSuit.HEAT),
                new Card("7", CardSuit.CLUB),
                new Card("6", CardSuit.SPADE),
                new Card("5", CardSuit.DIAMOND)
        ).sorted(Card.COMPARATOR).toList();
        cards.forEach(hand::analyse);
        HandScoring.Straight straight = new HandScoring.Straight();
        Rank rank = straight.doScoring(hand);
        assertTrue(rank instanceof Rank.Straight);
        assertEquals(50, rank.priority());
    }

    @Test
    void shouldScoreThreeOfKind() {
        HandStats hand = new HandStats();

        var cards = Stream.of(
                new Card("7", CardSuit.DIAMOND),
                new Card("7", CardSuit.HEAT),
                new Card("7", CardSuit.CLUB),
                new Card("K", CardSuit.SPADE),
                new Card("3", CardSuit.DIAMOND)
        ).sorted(Card.COMPARATOR).toList();
        cards.forEach(hand::analyse);
        HandScoring.ThreeOfKind threeOfKind = new HandScoring.ThreeOfKind();
        Rank rank = threeOfKind.doScoring(hand);
        assertTrue(rank instanceof Rank.ThreeOfKind);
        assertEquals(40, rank.priority());
    }

    @Test
    void shouldScoreTwoPair() {
        HandStats hand = new HandStats();

        var cards = Stream.of(
                new Card("4", CardSuit.DIAMOND),
                new Card("4", CardSuit.HEAT),
                new Card("3", CardSuit.CLUB),
                new Card("3", CardSuit.SPADE),
                new Card("A", CardSuit.DIAMOND)
        ).sorted(Card.COMPARATOR).toList();
        cards.forEach(hand::analyse);
        HandScoring.TwoPair twoPair = new HandScoring.TwoPair();
        Rank rank = twoPair.doScoring(hand);
        assertTrue(rank instanceof Rank.TwoPair);
        assertEquals(30, rank.priority());
    }

    @Test
    void shouldScorePair() {
        HandStats hand = new HandStats();

        var cards = Stream.of(
                new Card("A", CardSuit.DIAMOND),
                new Card("A", CardSuit.HEAT),
                new Card("8", CardSuit.CLUB),
                new Card("4", CardSuit.SPADE),
                new Card("7", CardSuit.DIAMOND)
        ).sorted(Card.COMPARATOR).toList();
        cards.forEach(hand::analyse);
        HandScoring.Pair pair = new HandScoring.Pair();
        Rank rank = pair.doScoring(hand);
        assertTrue(rank instanceof Rank.Pair);
        assertEquals(20, rank.priority());
    }

    @Test
    void shouldScoreHighestCard() {
        HandStats hand = new HandStats();
        var cards = Stream.of(
                new Card("3", CardSuit.DIAMOND),
                new Card("J", CardSuit.HEAT),
                new Card("8", CardSuit.CLUB),
                new Card("4", CardSuit.SPADE),
                new Card("2", CardSuit.DIAMOND)
        ).sorted(Card.COMPARATOR).toList();
        cards.forEach(hand::analyse);
        HandScoring.Highest highest = new HandScoring.Highest();
        Rank rank = highest.doScoring(hand);
        assertTrue(rank instanceof Rank.HighCard);
        Rank.HighCard highCardRank = (Rank.HighCard) rank;
        assertEquals(10, highCardRank.priority());
        assertEquals("J", highCardRank.getCard().value());
    }
}