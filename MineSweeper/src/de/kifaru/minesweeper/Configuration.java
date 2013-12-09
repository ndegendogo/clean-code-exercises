package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class Configuration {
    private int width;
    private int height;
    private List<Position> minePositions;
    
    static Configuration parse(String[] givenConfig) {
        Configuration config = new Configuration();
        config.height = givenConfig.length;
        if (givenConfig.length > 0) {
            config.width = givenConfig[0].length();
        } else {
            config.width = 0;
        }
        config.minePositions = findAllMinePositions(givenConfig);
        return config;
    }
    
    static List<Position> findAllMinePositions(String[] givenLines) {
        List<Position> mines = new ArrayList<Position>();
        for(int yPos = 0; yPos < givenLines.length; yPos ++) {
            mines.addAll(findMinesInLine(givenLines[yPos], yPos));
        }
        return mines;
    }

    private static List<Position> findMinesInLine(String givenLine, int yPos) {
        List<Position> mines = new ArrayList<Position>();
        int xPos = -1;
        boolean found = false;
        do {
            xPos = givenLine.indexOf('*', xPos + 1);
            found = (xPos >= 0);
            if (found) {
                mines.add(new Position(xPos, yPos));
            }
        } while (found);
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