package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class Configuration {
    private int width;
    private int height;
    private List<Position> mineFields;
    
    static Configuration parse(String[] givenBoard) {
        Configuration board = new Configuration();
        board.height = givenBoard.length;
        if (givenBoard.length > 0) {
            board.width = givenBoard[0].length();
        } else {
            board.width = 0;
        }
        board.mineFields = findAllMineFields(givenBoard);
        return board;
    }
    
    static List<Position> findAllMineFields(String[] givenLines) {
        List<Position> mineFields = new ArrayList<Position>();
        for(int yPos = 0; yPos < givenLines.length; yPos ++) {
            mineFields.addAll(findMinesInLine(givenLines[yPos], yPos));
        }
        return mineFields;
    }

    private static List<Position> findMinesInLine(String givenLine, int yPos) {
        List<Position> mineFields = new ArrayList<Position>();
        int xPos = -1;
        boolean found = false;
        do {
            xPos = givenLine.indexOf('*', xPos + 1);
            found = (xPos >= 0);
            if (found) {
                mineFields.add(new Position(xPos, yPos));
            }
        } while (found);
        return mineFields;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    List<Position> getMineFields() {
        return new ArrayList<Position>(mineFields);
    }

}