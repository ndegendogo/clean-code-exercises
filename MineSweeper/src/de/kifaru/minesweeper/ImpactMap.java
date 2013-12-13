package de.kifaru.minesweeper;

import java.util.HashMap;
import java.util.List;

// Review: ImpactMap should not extend HashMap or it should not be public.
// See Board for rationale.
// Besides, this seems to be used for counting the neighboring mines, right?
// Boards are guaranteed to be rectangular.
// ImpactMap could be replaced with an int[][].
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
