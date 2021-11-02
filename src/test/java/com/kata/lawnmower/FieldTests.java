package com.kata.lawnmower;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FieldTests {

    @Test
    void shouldTentouseMoveExample1() {
        var pelouse = new Field(5, 5);
        var result = pelouse.initLawnMowerPoint(1, 2, Direction.NORTH);
        result = pelouse.leftPivot(result);
        result = pelouse.advance(result);
        result = pelouse.leftPivot(result);
        result = pelouse.advance(result);
        result = pelouse.leftPivot(result);
        result = pelouse.advance(result);
        result = pelouse.leftPivot(result);
        result = pelouse.advance(result);
        result = pelouse.advance(result);
        assertNull(pelouse.getLawnMower(1, 2));
        assertEquals(new LawnMowerPoint(1, 3, Direction.NORTH), result);
    }

    @Test
    void shouldTentouseMoveExample2() {
        var pelouse = new Field(5, 5);
        var result = pelouse.initLawnMowerPoint(3, 3, Direction.EAST);
        result = pelouse.advance(result);
        result = pelouse.advance(result);
        result = pelouse.rightPivot(result);
        result = pelouse.advance(result);
        result = pelouse.advance(result);
        result = pelouse.rightPivot(result);
        result = pelouse.advance(result);
        result = pelouse.rightPivot(result);
        result = pelouse.rightPivot(result);
        result = pelouse.advance(result);
        assertNull(pelouse.getLawnMower(3, 3));
        assertEquals(new LawnMowerPoint(5, 1, Direction.EAST), result);
    }

}