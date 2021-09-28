package com.kata.pocker;

import java.util.Locale;
import java.util.Objects;

import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isNumeric;

final class CardValue {
    private static final String CARD_SYMBOLS = "(?i)[2-9]{1}|10|J|Q|K|A";
    static final CardValue LOWEST = new CardValue("2");

    private final String value;
    private final int priority;

    private CardValue(String value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    CardValue(String value) {
        this(validateValue(value), asPriority(value));
    }

    String value() {
        return value;
    }

    int cardScore() {
        if (hasNumericValue()) {
            return parseInt(value);
        }
        return switch (value.toUpperCase(Locale.ENGLISH)) {
            case "J", "Q", "K", "A" -> 10;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }

    int priority() {
        return priority;
    }

    boolean hasNumericValue() {
        return isNumeric(value);
    }

    private static String validateValue(String value) {
        if (!value.matches(CARD_SYMBOLS)) {
            throw new IllegalArgumentException("Invalid card value");
        }
        return value;
    }

    private static int asPriority(String value) {
        if (isNumeric(value)) {
            return parseInt(value) - 1;
        }
        return switch (value.toUpperCase(Locale.ENGLISH)) {
            case "J" -> 10;
            case "Q" -> 11;
            case "K" -> 12;
            case "A" -> 13;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardValue cardValue = (CardValue) o;
        return Objects.equals(value, cardValue.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "CardValue{" +
                "value='" + value + '\'' +
                ", priority=" + priority +
                '}';
    }
}