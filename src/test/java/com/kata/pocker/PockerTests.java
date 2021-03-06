package com.kata.pocker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PockerTests {

    @Test
    void shouldMrWhiteWinsWithHighCardAce() {
        ScoreSet scoreSet = Pocker.handRanks()
            .player("Black")
                .hands("2H", "3D", "5S", "9C", "KD")
            .player("White")
                .hands("2C", "3H", "4S", "8C", "AH")
            .score();

        assertEquals("White wins. - with high card: Ace", scoreSet.printWinnerReport());
    }

    @Test
    void shouldMrBlackWinsWithFullHouseRank() {
        ScoreSet scoreSet = Pocker.handRanks()
            .player("Black")
                .hands("2H", "4S", "4C", "2D", "4H")
            .player("White")
                .hands("2S", "8S", "AS", "QS", "3S")
            .score();

        assertEquals("Black wins. - with full house", scoreSet.printWinnerReport());
    }

    @Test
    void shouldMrBlackWinsWithHighCard() {
        ScoreSet scoreSet = Pocker.handRanks()
            .player("Black")
                .hands("2H", "3D", "5S", "9C", "KD")
            .player("White")
                .hands("2C", "3H", "4S", "8C", "KH")
            .score();

        assertEquals("Black wins. - with high card: 9", scoreSet.printWinnerReport());
    }

    @Test
    void shouldMrBlueWinsWithRoyalFlush() {
        ScoreSet scoreSet = Pocker.handRanks()
            .player("Blue")
                .hands("TH", "JH", "KH", "QH", "AH")
            .player("White")
                .hands("2C", "3H", "5C", "9S", "KH")
            .score();

        assertEquals("Blue wins. - with royal flush", scoreSet.printWinnerReport());
    }

    @Test
    void shouldMrBrownWinsWithRoyalFlush() {
        ScoreSet scoreSet = Pocker.handRanks()
            .player("Blue")
                .hands("2D", "JH", "KH", "QH", "AH")
            .player("Brown")
                .hands("TD", "JD", "KD", "QD", "AD")
            .score();

        assertEquals("Brown wins. - with royal flush", scoreSet.printWinnerReport());
    }

    @Test
    void shouldMrYellowWinsWithRoyalFlush() {
        ScoreSet scoreSet = Pocker.handRanks()
            .player("Yellow")
                .hands("4C", "5C", "6C", "7C", "8C")
            .player("Brown")
                .hands("4C", "5D", "6H", "7S", "8S")
            .score();

        assertEquals("Yellow wins. - with straight flush", scoreSet.printWinnerReport());
    }

    @Test
    void shouldMrPinkWinsWithRoyalFlush() {
        ScoreSet scoreSet = Pocker.handRanks()
            .player("Yellow")
                .hands("9C", "6H", "2D", "3H", "3S")
            .player("Pink")
                .hands("4S", "5S", "6S", "7S", "8S")
            .score();

        assertEquals("Pink wins. - with straight flush", scoreSet.printWinnerReport());
    }

    @Test
    void shouldMrGrayWinsWithFourOfKing() {
        ScoreSet scoreSet = Pocker.handRanks()
            .player("Gray")
                .hands("9C", "9H", "9D", "9S", "3S")
            .player("Pink")
                .hands("2S", "4D", "2D", "7C", "6H")
            .score();

        assertEquals("Gray wins. - with four of a king", scoreSet.printWinnerReport());
    }

    @Test
    void shouldMrGreenWinsWithFlush() {
        ScoreSet scoreSet = Pocker.handRanks()
            .player("Yellow")
                .hands("9C", "6H", "2D", "3H", "3S")
            .player("Green")
                .hands("7H", "5H", "TH", "AH", "8H")
            .score();

        assertEquals("Green wins. - with flush", scoreSet.printWinnerReport());
    }

    @Test
    void shouldMrBlackWinsWithStraight() {
        ScoreSet scoreSet = Pocker.handRanks()
            .player("Black")
                .hands("7C", "8H", "9D", "TC", "JS")
            .player("Green")
                .hands("2H", "5D", "TC", "AS", "9H")
            .score();

        assertEquals("Black wins. - with straight", scoreSet.printWinnerReport());
    }

    @Test
    void shouldMrKingWinsWithThreeOfKing() {
        ScoreSet scoreSet = Pocker.handRanks()
            .player("Black")
                .hands("3C", "5H", "9D", "AC", "JS")
            .player("King")
                .hands("AH", "AD", "AC", "4S", "9H")
            .score();

        assertEquals("King wins. - with three of a king", scoreSet.printWinnerReport());
    }

    @Test
    void shouldMrRedWinsWithTwoOfPair() {
        ScoreSet scoreSet = Pocker.handRanks()
            .player("Red")
                .hands("AC", "AH", "4D", "4S", "QS")
            .player("King")
                .hands("4H", "2D", "AC", "5S", "9H")
            .score();

        assertEquals("Red wins. - with two pair", scoreSet.printWinnerReport());
    }

    @Test
    void shouldMrPinkWinsWithPair() {
        ScoreSet scoreSet = Pocker.handRanks()
            .player("Red")
                .hands("KC", "4C", "7D", "5H", "QS")
            .player("Pink")
                .hands("3H", "KD", "4C", "4S", "TH")
            .score();

        assertEquals("Pink wins. - with pair", scoreSet.printWinnerReport());
    }

    @Test
    void shouldThrowsInvalidPlayerNameWhenTheNameIsAlreadyUsed() {
        var exception = assertThrows(IllegalArgumentException.class, () -> Pocker.handRanks()
            .player("Blue")
                .hands("2H", "3D", "5S", "9C", "KD")
            .player("Blue")
            .score());

        assertEquals(exception.getMessage(), "Invalid player name, this name Blue is already exist");
    }

    @Test
    void shouldThrowsExceptionWhenPlayerNameIsInvalid() {
        var exception = assertThrows(IllegalArgumentException.class, () -> Pocker.handRanks()
                .player("Hi")
                .hands("2H", "3D", "5S", "9C", "KD")
        );

        assertEquals(exception.getMessage(), "Invalid player name Hi");
    }

    @ParameterizedTest
    @MethodSource("similarPlayerHands")
    void shouldTie(String[] playerHand1, String[] playerHand2) {
        ScoreSet scoreSet = Pocker.handRanks()
            .player("Black")
                .hands(playerHand1)
            .player("White")
                .hands(playerHand2)
            .score();

        assertEquals("Tie.", scoreSet.printWinnerReport());
    }

    static Stream<Arguments> similarPlayerHands() {
        return Stream.of(
                Arguments.of(new String[]{"2H", "3D", "5S", "9C", "KD"}, new String[]{"2C", "3H", "5C", "9S", "KH"}),
                Arguments.of(new String[]{"2H", "3D", "5S", "9C", "KD"}, new String[]{"2C", "3H", "5C", "9S", "KH"}),
                Arguments.of(new String[]{"9C", "9H", "9D", "9S", "3S"}, new String[]{"KC", "KH", "KD", "KS", "3S"}),
                Arguments.of(new String[]{"7C", "8H", "9D", "TC", "JS"}, new String[]{"2H", "3D", "4S", "5C", "6D"}),
                Arguments.of(new String[]{"TH", "JH", "KH", "QH", "AH"}, new String[]{"TC", "JC", "KC", "QC", "AC"})
        );
    }
}
