package de.kifaru.minesweeper;

public class Field implements Comparable<Field> {
    int x;
    int y;
    
    Field(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object other) {
        if ((other != null) && (other instanceof Field)) {
            return (x == ((Field)other).x) && (y == ((Field)other).y);
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
    public int compareTo(Field other) {
        if (this.y < other.y) return -1;
        else if (this.y > other.y) return 1;
        else if (this.x < other.x) return -1;
        else if (this.x > other.x) return 1; 
        else return 0;
    }
}