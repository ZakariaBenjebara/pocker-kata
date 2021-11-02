package com.kata.lawnmower;

public record Point(int x, int y) {

    public Point incHorizontal() {
        return new Point(x + 1, y);
    }

    public Point decHorizontal() {
        return new Point(x - 1, y);
    }

    public Point incVertical() {
        return new Point(x, y + 1);
    }

    public Point decVertical() {
        return new Point(x, y - 1);
    }
}
