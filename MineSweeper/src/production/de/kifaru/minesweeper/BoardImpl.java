package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class BoardImpl implements Board {

    private final int width;
    private final int height;
    private final Field fields[][];
    
    private BoardImpl(final int width, final int height) {
        this.width = width;
        this.height = height;
        fields = new Field[height][width];
        for(int j = 0; j < height; j ++) {
            for (int i = 0; i < width; i ++) {
                fields[j][i] = new Field();
            }
        }
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
            addImpact(pos.getX(), pos.getY());
        }
    }
    
    private void addImpact(final int x, final int y) {
        if (!outOfBounds(x, y)) {
            final Field field = getField(x, y);
            field.addImpact(1);
        }
    }

    private boolean outOfBounds(final int x, final int y) {
        return (x < 0 || x >= width || y < 0 || y >= height);
    }

    @Override
    public Field getField(final int colNumber, final int lineNumber) {
        return fields[lineNumber][colNumber];
    }

    private void putMines(final Iterable<Position> positions) {
        for (final Position pos : positions) {
            putMine(pos.getX(), pos.getY());
        }
    }

    private void putMine(final int x, final int y) {
        final Field field = getField(x, y);
        field.putMine();
    }
    
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
