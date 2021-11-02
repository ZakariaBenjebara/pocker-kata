package com.kata.lawnmower;

import static java.util.Optional.ofNullable;

public final class Field {
    private final int horizontalBound;
    private final int verticalBound;
    private final LawnMowerPoint[][] matrix;

    public Field(int horizontalBound, int verticalBound) {
        this.horizontalBound = horizontalBound;
        this.verticalBound = verticalBound;
        this.matrix = new LawnMowerPoint[horizontalBound + 1][verticalBound + 1];
    }

    public LawnMowerPoint initLawnMowerPoint(int x, int y, Direction direction) {
        return this.matrix[x][y] = new LawnMowerPoint(x, y, direction);
    }

    public LawnMowerPoint rightPivot(LawnMowerPoint point) {
       return matrix[point.getX()][point.getY()] = point.rightPivot();
    }

    public LawnMowerPoint leftPivot(LawnMowerPoint point) {
        return matrix[point.getX()][point.getY()] = point.leftPivot();
    }

    public LawnMower getLawnMower(int x, int y) {
        LawnMowerPoint point = matrix[x][y];
        return ofNullable(point)
                .map(LawnMowerPoint::getLawnMower)
                .orElse(null);
    }

    public LawnMowerPoint advance(LawnMowerPoint currentPoint) {
        var targetPoint = currentPoint.move();
        if (isOutOfBounds(targetPoint)) {
            return currentPoint;
        }
        if (checkAvailablePosition(targetPoint)) {
            clearPoint(currentPoint);
            return updatePoint(targetPoint);
        }
        return currentPoint;
    }

    private boolean checkAvailablePosition(LawnMowerPoint point) {
        return null == matrix[point.getX()][point.getY()];
    }

    private void clearPoint(LawnMowerPoint point) {
        matrix[point.getX()][point.getY()] = null;
    }

    private LawnMowerPoint updatePoint(LawnMowerPoint point) {
        return matrix[point.getX()][point.getY()] = point;
    }

    private boolean isOutOfBounds(LawnMowerPoint targetPoint) {
        return targetPoint.getY() > verticalBound
                || targetPoint.getX() > horizontalBound;
    }
}
