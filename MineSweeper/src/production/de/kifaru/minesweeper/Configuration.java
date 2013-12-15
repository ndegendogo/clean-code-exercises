package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class Configuration {
    private final int width;
    private final int height;
    private final List<Position> minePositions;
    
    private Configuration(final int width, final int height, final List<Position> minePositions) {
        this.width = width;
        this.height = height;
        this.minePositions = minePositions;
    }
    
    static Configuration parse(final String[] givenConfig) {
        final int height = givenConfig.length;
        final int width = (givenConfig.length > 0) ? givenConfig[0].length() : 0;
        final List<Position> minePositions = findAllMinePositions(givenConfig);
        return new Configuration(width, height, minePositions);
    }
    
    static List<Position> findAllMinePositions(final String[] givenLines) {
        final List<Position> mines = new ArrayList<Position>();
        for(int yPos = 0; yPos < givenLines.length; yPos ++) {
            mines.addAll(findMinesInLine(givenLines[yPos], yPos));
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

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    List<Position> getMinePositions() {
        return new ArrayList<Position>(minePositions);
    }

}