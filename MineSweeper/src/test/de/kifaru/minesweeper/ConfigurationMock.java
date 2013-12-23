package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class ConfigurationMock implements Configuration {
    private final int width;
    private final int height;
    private final List<Position> minePositions;

    ConfigurationMock(final int width, final int height) {
        this.width = width;
        this.height = height;
        minePositions = new ArrayList<Position>();
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
    public Iterable<Position> getMinePositions() {
        return new ArrayList<Position>(minePositions);
    }
}
