package com.arcane.board;

public enum Direction {
    North, South, East, West;

    public Direction opposite() {
        if (this.equals(North)) {
            return South;
        }
        if (this.equals(South)) {
            return North;
        }
        if (this.equals(East)) {
            return West;
        }

        return East;
    }
}
