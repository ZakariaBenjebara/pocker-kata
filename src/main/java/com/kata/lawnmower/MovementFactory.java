package com.kata.lawnmower;

public class MovementFactory {
    private MovementFactory() {}

    public static Movement createFrom(Direction orientation) {
        return switch (orientation) {
            case EAST -> new Movement.EastMovement();
            case WEST -> new Movement.WestMovement();
            case NORTH -> new Movement.NorthMovement();
            case SOUTH -> new Movement.SouthMovement();
        };
    }
}
