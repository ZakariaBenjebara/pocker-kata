package com.kata.lawnmower;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovementTests {

    @Test
    void shouldEastDoUpVerticalMove() {
        var movement = MovementFactory.createFrom(Direction.NORTH);
        Point target = movement.doMove(new Point(2, 3));
        assertEquals(new Point(2, 4), target);
    }

    @Test
    void shouldEastDoDownVerticalMove() {
        var movement = MovementFactory.createFrom(Direction.SOUTH);
        Point target = movement.doMove(new Point(20, 16));
        assertEquals(new Point(20, 15), target);
    }


    @Test
    void shouldEastDoLeftHorizontalMove() {
        var movement = MovementFactory.createFrom(Direction.WEST);
        Point target = movement.doMove(new Point(78, 91));
        assertEquals(new Point(77, 91), target);
    }

    @Test
    void shouldEastDoRightHorizontalMove() {
        var movement = MovementFactory.createFrom(Direction.EAST);
        Point target = movement.doMove(new Point(56, 10));
        assertEquals(new Point(57, 10), target);
    }

}