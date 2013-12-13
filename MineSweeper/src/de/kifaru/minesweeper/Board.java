package de.kifaru.minesweeper;

import java.util.HashMap;
import java.util.List;

// Review: You're making good progress.
// The code looks much more "Java" than before.
// You successfully learn and apply more and more patterns and idioms from the Java world.

// Review: source structure
// Currently, sources and tests are in the same directory.
// They can be in the same logical package, right.
// I personally even prefer if tests are in the same logical package.
// But I would still separate production code and test code physically.
// I'd go for this structure:
// src/production/de/kifaru/minesweeper/ <- production code
// src/test/de/kifaru/minesweeper/ <- test code

// Review: complexity
// I think the code and the design are too complex.
// I think too much code was written without a mandate by a test.
// I think that some tests, like ConfigurationTest, have very good ideas.
// At the same time, ConfigurationTest mandates a certain design that is not evident from the use case.
// Try going for the simplest solutions that could possibly work - of course not violating design principles.

// Review: final...
// I personally agree with the approach.
// It encourages functional programming.
// And pure functions are heavenly trivial to test.
// But it should be used consistently.
// Check your IDE for settings that warn you if you forget final.

// Note: If I were to redesign Java, I would make final the default on all variables.
// There would be no final keyword for variables.
// There would be a var keyword for variables which are not final.

// Review: Board should not extend HashMap.
// Extending HashMap allows callers of Board to call arbitrary HashMap methods.
// Thus, users of Board could mess up the internal data structures of Board.
// Apply the refactoring "replace inheritance with delegation".

// Review: Why does the board not know its width and height?
// Review: Why does the board have no configuration?

// Review: public classes vs. non-public classes.
// What was the rationale for making some classes public, others not public?
public class Board extends HashMap<Position, Field> {

    private static final long serialVersionUID = 1L;

    // Review: It seems that theMine is the singleton instance of MineField.
    // Singletons are evil.
    // But if you use singletons, you should at least follow the singleton design pattern.
    // Same fot theEmptyField.
    private static final MineField theMine = new MineField();
    private static final ImpactField theEmptyField = new ImpactField(0);
    
    public Board() {
    }
    
    public Board (final ImpactMap map) {
        this.putAll(map);
    }
    
    void putMine(final Position pos) {
        put(pos, theMine);
    }
    
    void putMines(final List<Position> positions) {
        // Review: should be (spaces, final)
        // for (final Position pos : positions) {
        for(Position pos:positions) {
            putMine(pos);
        }
    }

    // Review: This method does 2 things.
    // It gets the field of the specified position.
    // And it takes care that if the Map is unmapped, a default value is supplied.
    // The latter should be extracted into a class of its own, for reuse.

    // Note: I personally wish that Java would overload the || operator for references to allow writing the code like this:
    // return get(new Position(colNumber, lineNumber)) || theEmptyField;
    Field getField(final int colNumber, final int lineNumber) {
        // final missing
        Field field = get(new Position(colNumber, lineNumber));
        // simplify to
        // return field != null ? field : theEmptyField;
        if (field == null) {
            return theEmptyField;
        } else {
            return field;
        }
    }
}
