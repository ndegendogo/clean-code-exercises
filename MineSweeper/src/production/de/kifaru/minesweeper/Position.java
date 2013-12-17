package de.kifaru.minesweeper;

class Position {
    private final int x;
    private final int y;
    
    Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof Position) {
            return (x == ((Position)other).x) && (y == ((Position)other).y);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return x ^ (y << 16);
    }
    
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}