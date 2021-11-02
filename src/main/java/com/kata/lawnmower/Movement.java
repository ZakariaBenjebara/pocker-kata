package com.kata.lawnmower;

@FunctionalInterface
public interface Movement {
    Point doMove(Point point);

    final class NorthMovement implements Movement {

        @Override
        public Point doMove(Point point) {
            return point.incVertical();
        }
    }

    final class SouthMovement implements Movement {

        @Override
        public Point doMove(Point point) {
            return point.decVertical();
        }
    }

    final class WestMovement implements Movement {

        @Override
        public Point doMove(Point point) {
            return point.decHorizontal();
        }
    }


    final class EastMovement implements Movement {

        @Override
        public Point doMove(Point point) {
            return point.incHorizontal();
        }
    }
}
