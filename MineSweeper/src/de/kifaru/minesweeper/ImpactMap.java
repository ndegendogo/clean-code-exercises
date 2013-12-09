package de.kifaru.minesweeper;

import java.util.HashMap;
import java.util.List;

public class ImpactMap extends HashMap<Position, ImpactField> {

    private static final long serialVersionUID = 1L;

    void addImpact(Position pos) {
        ImpactField prevValue = get(pos);
        if (prevValue == null) {
            put(pos, new ImpactField());
        } else {
            prevValue.addImpact(1);
        }
    }

    void addImpact(List<Position> positions) {
        for (Position pos:positions) {
            addImpact(pos);
        }
    }
}
