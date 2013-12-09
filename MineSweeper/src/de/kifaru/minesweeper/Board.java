package de.kifaru.minesweeper;

import java.util.HashMap;
import java.util.List;

public class Board extends HashMap<Position, Field> {

    private static final long serialVersionUID = 1L;

    void putMine(Position pos) {
        Field prevValue = get(pos);
        if (prevValue == null || !(prevValue instanceof MineField)) {
            Field value = new MineField();
            put(pos, value);
        }
    }
    
    void putMines(List<Position> positions) {
        for(Position pos:positions) {
            putMine(pos);
        }
    }

    void addImpact(Position pos) {
        Field prevValue = get(pos);
        if (prevValue == null) {
            Field value = new ImpactField();
            put(pos, value);
        } else if (prevValue instanceof ImpactField) {
            ((ImpactField)prevValue).addImpact(1);
        }
    }

    void addImpact(List<Position> positions) {
        for (Position pos:positions) {
            addImpact(pos);
        }
    }
}
