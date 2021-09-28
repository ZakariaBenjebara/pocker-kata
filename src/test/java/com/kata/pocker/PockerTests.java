package com.kata.pocker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PockerTests {

    @Test
    void shouldMrWhiteWinsWithHighCardAce() {
        SetScore setScore = Pocker.handRanks()
            .player("Black")
                .hands("2H", "3D", "5S", "9C", "KD")
            .player("White")
                .hands("2C", "3H", "4S", "8C", "AH")
            .score();

        assertEquals("White wins. - with high card: Ace", setScore.printWinnerReport());
    }


    @Test
    void shouldMrBlackWinsWithFullHouseRank() {
        SetScore setScore = Pocker.handRanks()
            .player("Black")
                .hands("2H", "4S", "4C", "2D", "4H")
            .player("White")
                .hands("2S", "8S", "AS", "QS", "3S")
            .score();

        assertEquals("Black wins. - with full house", setScore.printWinnerReport());
    }

    @Test
    void shouldMrBlackWinsWithHighCard() {
        SetScore setScore = Pocker.handRanks()
            .player("Black")
                .hands("2H", "3D", "5S", "9C", "KD")
            .player("White")
                .hands("2C", "3H", "4S", "8C", "KH")
            .score();

        assertEquals("Black wins. - with high card: 9", setScore.printWinnerReport());
    }

    @Test
    void shouldMrWhiteWinsWithRoyalFlush() {
        SetScore setScore = Pocker.handRanks()
                .player("Blue")
                .hands("TH", "JH", "KH", "QH", "AH")
                .player("White")
                .hands("2C", "3H", "5C", "9S", "KH")
                .score();

        assertEquals("Blue wins. - with royal flush", setScore.printWinnerReport());
    }

    @Test
    void shouldTie() {
        SetScore setScore = Pocker.handRanks()
            .player("Black")
                .hands("2H", "3D", "5S", "9C", "KD")
            .player("White")
                .hands("2C", "3H", "5C", "9S", "KH")
            .score();

        assertEquals("Tie.", setScore.printWinnerReport());
    }
}
