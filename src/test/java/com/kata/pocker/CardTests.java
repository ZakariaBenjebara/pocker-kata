package com.kata.pocker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CardTests {

    @Test
    void shouldThrowsWhenInvalidCard() {
        assertThrows(IllegalArgumentException.class, () -> new CardValue("1"));
    }

    @Test
    void shouldCreate2Card() {
        var cardValue = new CardValue("2");
        assertEquals(1, cardValue.priority());
        assertEquals(2, cardValue.cardScore());
    }

    @Test
    void shouldCreate3Card() {
        var cardValue = new CardValue("3");
        assertEquals(2, cardValue.priority());
        assertEquals(3, cardValue.cardScore());
    }

    @Test
    void shouldCreate4Card() {
        var cardValue = new CardValue("4");
        assertEquals(3, cardValue.priority());
        assertEquals(4, cardValue.cardScore());
    }

    @Test
    void shouldCreate5Card() {
        var cardValue = new CardValue("5");
        assertEquals(4, cardValue.priority());
        assertEquals(5, cardValue.cardScore());
    }

    @Test
    void shouldCreate6Card() {
        var cardValue = new CardValue("6");
        assertEquals(5, cardValue.priority());
        assertEquals(6, cardValue.cardScore());
    }

    @Test
    void shouldCreate7Card() {
        var cardValue = new CardValue("7");
        assertEquals(6, cardValue.priority());
        assertEquals(7, cardValue.cardScore());
    }

    @Test
    void shouldCreate8Card() {
        var cardValue = new CardValue("8");
        assertEquals(7, cardValue.priority());
        assertEquals(8, cardValue.cardScore());
    }

    @Test
    void shouldCreate9Card() {
        var cardValue = new CardValue("9");
        assertEquals(8, cardValue.priority());
        assertEquals(9, cardValue.cardScore());
    }

    @Test
    void shouldCreate10Card() {
        var cardValue = new CardValue("T");
        assertEquals(9, cardValue.priority());
        assertEquals(10, cardValue.cardScore());
    }

    @Test
    void shouldCreateJCard() {
        var cardValue = new CardValue("J");
        assertEquals(10, cardValue.priority());
        assertEquals(10, cardValue.cardScore());
    }

    @Test
    void shouldCreateQCard() {
        var cardValue = new CardValue("Q");
        assertEquals(11, cardValue.priority());
        assertEquals(10, cardValue.cardScore());
    }

    @Test
    void shouldCreateKCard() {
        var cardValue = new CardValue("K");
        assertEquals(12, cardValue.priority());
        assertEquals(10, cardValue.cardScore());
    }

    @Test
    void shouldCreateACard() {
        var cardValue = new CardValue("A");
        assertEquals(13, cardValue.priority());
        assertEquals(10, cardValue.cardScore());
    }

    @ParameterizedTest
    @MethodSource("orderedByNextPriorityCards")
    void shouldCardHasNextPriority(Card card1, Card card2) {
        var result = card2.equalsPreviewsPriority(card1);
        assertTrue(result);
    }

    @Test
    void shouldntCardHasNextPriority() {
        var card1 = Card.from("5H");
        var card2 = Card.from("AD");
        var result = card2.equalsPreviewsPriority(card1);
        assertFalse(result);
    }

    @ParameterizedTest
    @MethodSource("sameCardSuits")
    void shouldCardHasSameSuit(Card card1, Card card2) {
        var result = card2.hasSameSuit(card1);
        assertTrue(result);
    }

    @Test
    void shouldntCardHasSameSuit() {
        var card1 = Card.from("3C");
        var card2 = Card.from("KS");
        var result = card2.hasSameSuit(card1);
        assertFalse(result);
    }

    static Stream<Arguments> orderedByNextPriorityCards() {
        return Stream.of(
                Arguments.of(Card.from("2H"), Card.from("3D")),
                Arguments.of(Card.from("3S"), Card.from("4C")),
                Arguments.of(Card.from("5H"), Card.from("6D")),
                Arguments.of(Card.from("7S"), Card.from("8C")),
                Arguments.of(Card.from("9H"), Card.from("TD")),
                Arguments.of(Card.from("JS"), Card.from("QC")),
                Arguments.of(Card.from("KH"), Card.from("AD"))
        );
    }

    static Stream<Arguments> sameCardSuits() {
        return Stream.of(
                Arguments.of(Card.from("2H"), Card.from("3H")),
                Arguments.of(Card.from("3D"), Card.from("4D")),
                Arguments.of(Card.from("5C"), Card.from("2C")),
                Arguments.of(Card.from("7S"), Card.from("4S")),
                Arguments.of(Card.from("9H"), Card.from("TH")),
                Arguments.of(Card.from("QS"), Card.from("AS")),
                Arguments.of(Card.from("8C"), Card.from("6C"))
        );
    }
}