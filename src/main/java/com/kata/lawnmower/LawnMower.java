package com.kata.lawnmower;

public record LawnMower(Direction direction) {

    public LawnMower rightPivot() {
        return new LawnMower(direction.turnRight());
    }

    public LawnMower leftPivot() {
        return new LawnMower(direction.turnLeft());
    }

    public Direction getDirection() {
        return direction;
    }
}
