package de.kifaru.minesweeper;

public class Position implements Comparable<Position> {
    private final int x;
    private final int y;
    
    Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(final Object other) {
        if ((other != null) && (other instanceof Position)) {
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

    @Override
    public int compareTo(final Position other) {
        if (this.y < other.y) return -1;
        else if (this.y > other.y) return 1;
        else if (this.x < other.x) return -1;
        else if (this.x > other.x) return 1; 
        else return 0;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}