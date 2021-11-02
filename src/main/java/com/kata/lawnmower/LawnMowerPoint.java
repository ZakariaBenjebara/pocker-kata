package com.kata.lawnmower;

import java.util.Objects;

public final class LawnMowerPoint {
    private final Point point;
    private final LawnMower lawnMower;
    private final Movement movement;

    private LawnMowerPoint(Point point, LawnMower lawnMower) {
        this.point = point;
        this.lawnMower = lawnMower;
        this.movement = MovementFactory.createFrom(lawnMower.getDirection());
    }

    public LawnMowerPoint(int x, int y, Direction orientation) {
        this(new Point(x, y), new LawnMower(orientation));
    }

    public LawnMowerPoint rightPivot() {
        return new LawnMowerPoint(point, lawnMower.rightPivot());
    }

    public LawnMowerPoint leftPivot() {
        return new LawnMowerPoint(point, lawnMower.leftPivot());
    }

    public LawnMowerPoint move() {
        return new LawnMowerPoint(movement.doMove(point), lawnMower);
    }

    public int getX() {
        return point.x();
    }

    public int getY() {
        return point.y();
    }

    public LawnMower getLawnMower() {
        return lawnMower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LawnMowerPoint that = (LawnMowerPoint) o;
        return Objects.equals(point, that.point)
                && Objects.equals(lawnMower, that.lawnMower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, lawnMower);
    }

    @Override
    public String toString() {
        return "TendousePoint{" +
                "point=" + point +
                ", tendouse=" + lawnMower +
                '}';
    }
}
