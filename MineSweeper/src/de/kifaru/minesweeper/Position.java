package de.kifaru.minesweeper;

// Review: Good: Immutable value class.
// Review: Good: Learning how to implement Comparable.
// Review: Is comparable necessary for this class?
// It makes sense in case of reuse.
// But if it is for reuse, it should go into a different package.
// Review: Is this class really necessary?
// The Map<Position, Field> could be replaced by a Field[][].
public class Position implements Comparable<Position> {
    // Review: fields should be encapsulated (even if final).
    final int x;
    final int y;

    // Review: missing final for parameters.
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(final Object other) {
        // Review: Check for null redundant.
        // instanceof is a runtime check.
        // At runtime, null has no type.
        // Thus null instanceof X always is false.
        // Even null instanceof Object is false.
        if ((other != null) && (other instanceof Position)) {
            // Review: direct return of boolean - very nice.
            return (x == ((Position)other).x) && (y == ((Position)other).y);
        }
        return false;
    }

    @Override
    public int hashCode() {
        // Review: symmetric hashCode, inefficient.
        // How about x + (y << 16);
        return x + y;
    }

    @Override
    public String toString() {
        // Review: (x,y) is a well known notation.
        // So why not
        // return String.format("(%d,%d)", x, y);?
        // Or
        // return "(" + x + "," + y + ")";
        return "x = " + x + ", y = " + y;
    }

    @Override
    public int compareTo(final Position other) {
        // Review: this qualifier is unnecessary here.
        if (this.y < other.y) return -1;
        else if (this.y > other.y) return 1;
        else if (this.x < other.x) return -1;
        else if (this.x > other.x) return 1; 
        else return 0;
    }
}