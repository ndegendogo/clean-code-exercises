package de.kifaru.minesweeper;

import static de.kifaru.minesweeper.MineSweeperException.ErrorCode;

import java.util.ArrayList;
import java.util.List;

class ConfigurationImpl implements Configuration {
    private final int width;
    private final int height;
    private final List<Position> minePositions;
    
    private ConfigurationImpl(final List<String> config, final List<Position> minePositions) {
        this.width = config.get(0).length();
        this.height = config.size();
        this.minePositions = minePositions;
    }
    
    static Configuration parse(final List<String> config) {
        checkDimensions(config);
        final List<Position> minePositions = findAllMinePositions(config);
        return new ConfigurationImpl(config, minePositions);
    }

    private static void checkDimensions(final List<String> config) {
        if (config.size() == 0 || config.get(0).length() == 0) {
            throw new MineSweeperException(ErrorCode.ILLEGAL_CONFIGURATION);
        }
    }
    
    static List<Position> findAllMinePositions(final List<String> lines) {
        final List<Position> mines = new ArrayList<Position>();
        for(int y = 0; y < lines.size(); y ++) {
            mines.addAll(findMinesInLine(lines.get(y), y));
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