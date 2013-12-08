package de.kifaru.minesweeper;

class Position{
    int x;
    int y;
    
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean equals(Object other) {
        if ((other != null) && (other instanceof Position)) {
            return (x == ((Position)other).x) && (y == ((Position)other).y);
        }
        return false;
    }
    
    public int hashCode() {
        return x + y;
    }
    
    public String toString() {
        return "x = " + x + ", y = " + y;
    }
}