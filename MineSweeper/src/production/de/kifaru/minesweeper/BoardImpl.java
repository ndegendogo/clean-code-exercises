package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class BoardImpl implements Board {

    private static final long serialVersionUID = 1L;
    private static final MineField theMine = new MineField();
    private static final ImpactField theEmptyField = new ImpactField(0);
    private final int width;
    private final int height;
    private final HashMap<Position, Field> fields;
    
    private BoardImpl(final int width, final int height) {
        this.width = width;
        this.height = height;
        fields = new HashMap<Position, Field>();
    }
    
    public BoardImpl(final Configuration config) {
        this(config.getWidth(), config.getHeight());
        Iterable<Position> positions = config.getMinePositions();
        final List<Position> impactRegions = getImpactRegions(positions);
        addImpact(impactRegions);
        putMines( positions);
    }
    
    private static List<Position> getImpactRegions(final Iterable<Position> minePositions) {
        final List<Position> impactRegions = new ArrayList<Position>();
        for (final Position mine : minePositions) {
            impactRegions.addAll(getImpactRegion(mine));
        }
        return impactRegions;
    }

    private static List<Position> getImpactRegion(final Position mine) {
        final List<Position> impactRegion = new ArrayList<Position>();
        for (int x = mine.getX() - 1; x <= mine.getX() + 1; x ++) {
            for (int y = mine.getY() - 1; y <= mine.getY() + 1; y ++) {
                impactRegion.add(new Position(x, y));
            }
        }
        return impactRegion;
    }
    
    private void addImpact(final Iterable<Position> positions) {
        for (final Position pos : positions) {
            addImpact(pos);
        }
    }
    
    void addImpact(final Position pos) {
        final ImpactField prevValue = (ImpactField)fields.get(pos);
        if (prevValue == null) {
            fields.put(pos, new ImpactField(1));
        } else {
            prevValue.addImpact(1);
        }
    }

    private void putMines(final Iterable<Position> positions) {
        for (final Position pos : positions) {
            putMine(pos);
        }
    }

    void putMine(final Position pos) {
        fields.put(pos, theMine);
    }
    
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Field getField(final int colNumber, final int lineNumber) {
        final Field field = fields.get(new Position(colNumber, lineNumber));
        return defaultToEmptyFieldIfNull(field);
    }

    private Field defaultToEmptyFieldIfNull(final Field field) {
        return field == null ? theEmptyField : field;
    }

}
