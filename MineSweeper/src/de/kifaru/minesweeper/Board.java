package de.kifaru.minesweeper;

import java.util.HashMap;
import java.util.List;

public class Board extends HashMap<Position, Field> {

    private static final long serialVersionUID = 1L;
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
        for(Position pos:positions) {
            putMine(pos);
        }
    }

    Field getField(final int colNumber, final int lineNumber) {
        Field field = get(new Position(colNumber, lineNumber));
        if (field == null) {
            return theEmptyField;
        } else {
            return field;
        }
    }
}
