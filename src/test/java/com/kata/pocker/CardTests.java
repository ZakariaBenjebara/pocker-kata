package com.kata.pocker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        var cardValue = new CardValue("10");
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
}