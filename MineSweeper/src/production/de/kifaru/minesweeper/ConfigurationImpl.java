package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class ConfigurationImpl implements Configuration {
    private final int width;
    private final int height;
    private final List<Position> minePositions;
    
    private ConfigurationImpl(final int width, final int height, final List<Position> minePositions) {
        this.width = width;
        this.height = height;
        this.minePositions = minePositions;
    }
    
    static Configuration parse(final String[] config) {
        final int height = config.length;
        final int width = (config.length > 0) ? config[0].length() : 0;
        final List<Position> minePositions = findAllMinePositions(config);
        return new ConfigurationImpl(width, height, minePositions);
    }
    
    static List<Position> findAllMinePositions(final String[] lines) {
        final List<Position> mines = new ArrayList<Position>();
        for(int y = 0; y < lines.length; y ++) {
            mines.addAll(findMinesInLine(lines[y], y));
        }
        return mines;
    }

    private static List<Position> findMinesInLine(final String line, final int y) {
        final List<Position> mines = new ArrayList<Position>();
        for (int x = 0; x < line.length(); x ++ ) {
            if (line.charAt(x) == '*') {
                mines.add(new Position(x, y));
            }
        }
        return mines;
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