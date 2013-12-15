package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board extends HashMap<Position, Field> {

    private static final long serialVersionUID = 1L;
    private static final MineField theMine = new MineField();
    private static final ImpactField theEmptyField = new ImpactField(0);
    private final int width;
    private final int height;
    
    public Board(final int width, final int height) {
        this.width = width;
        this.height = height;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    void putMine(final Position pos) {
        put(pos, theMine);
    }
    
    void putMines(final Iterable<Position> positions) {
        addImpact(Board.getImpactRegions(positions));
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

    static List<Position> getImpactRegions(final Iterable<Position> minePositions) {
        final List<Position> impactRegions = new ArrayList<Position>();
        for (Position mine:minePositions) {
            impactRegions.addAll(getImpactRegion(mine));
        }
        return impactRegions;
    }

    static List<Position> getImpactRegion(final Position mine) {
        final List<Position> impactRegion = new ArrayList<Position>();
        for (int x = mine.getX() - 1; x <= mine.getX() + 1; x ++) {
            for (int y = mine.getY() - 1; y <= mine.getY() + 1; y ++) {
                impactRegion.add(new Position(x, y));
            }
        }
        return impactRegion;
    }
    
    void addImpact(final Position pos) {
        final ImpactField prevValue = (ImpactField)get(pos);
        if (prevValue == null) {
            put(pos, new ImpactField(1));
        } else {
            prevValue.addImpact(1);
        }
    }

    void addImpact(final Iterable<Position> positions) {
        for (Position pos:positions) {
            addImpact(pos);
        }
    }
    
}
