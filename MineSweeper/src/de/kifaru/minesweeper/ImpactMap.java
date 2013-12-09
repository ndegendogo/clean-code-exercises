package de.kifaru.minesweeper;

import java.util.HashMap;
import java.util.List;

public class ImpactMap extends HashMap<Position, ImpactField> {

    private static final long serialVersionUID = 1L;

    void addImpact(final Position pos) {
        final ImpactField prevValue = get(pos);
        if (prevValue == null) {
            put(pos, new ImpactField(1));
        } else {
            prevValue.addImpact(1);
        }
    }

    void addImpact(final List<Position> positions) {
        for (Position pos:positions) {
            addImpact(pos);
        }
    }
}
