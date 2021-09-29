package com.kata.pocker;

import static org.apache.commons.lang3.StringUtils.isBlank;

record PlayerName(String value) {
    PlayerName {
        if (isBlank(value) || value.length() < 3) {
            throw new IllegalArgumentException("Invalid player name "+ value);
        }
    }
}
