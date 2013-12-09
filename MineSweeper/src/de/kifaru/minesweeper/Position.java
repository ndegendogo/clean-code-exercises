package de.kifaru.minesweeper;

public class Position implements Comparable<Position> {
    final int x;
    final int y;
    
    Position(int x, int y) {
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
        return x + y;
    }
    
    @Override
    public String toString() {
        return "x = " + x + ", y = " + y;
    }

    @Override
    public int compareTo(final Position other) {
        if (this.y < other.y) return -1;
        else if (this.y > other.y) return 1;
        else if (this.x < other.x) return -1;
        else if (this.x > other.x) return 1; 
        else return 0;
    }
}