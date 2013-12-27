package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class ConfigurationImpl implements Configuration {
    private final int width;
    private final int height;
    private final List<Position> minePositions;
    
    private ConfigurationImpl(final String[] config, final List<Position> minePositions) {
        this.width = config[0].length();
        this.height = config.length;
        this.minePositions = minePositions;
    }
    
    static Configuration parse(final String[] config) {
        checkDimensions(config);
        final List<Position> minePositions = findAllMinePositions(config);
        return new ConfigurationImpl(config, minePositions);
    }

    private static void checkDimensions(final String[] config) {
        if (config.length == 0 || config[0].length() == 0) {
            throw new MineSweeperException(MineSweeperException.ErrorCode.ILLEGAL_CONFIGURATION);
        }
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
            if (isMineAtPosition(line, x)) {
                mines.add(new Position(x, y));
            }
        }
        return mines;
    }

    private static boolean isMineAtPosition(final String line, int x) {
        return line.charAt(x) == '*';
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