package de.kifaru.minesweeper;

import java.util.ArrayList;

class Board {
    private int width;
    private int height;
    private ArrayList<Position> minePositions;
    
    static Board parse(String[] givenBoard) {
        Board board = new Board();
        board.height = givenBoard.length;
        board.width = givenBoard[0].length();
        board.minePositions = findAllMinePositions(givenBoard);
        return board;
    }
    
    static ArrayList<Position> findAllMinePositions(String[] givenLines) {
        ArrayList<Position> minePositions = new ArrayList<Position>();
        for(int yPos = 0; yPos < givenLines.length; yPos ++) {
            minePositions.addAll(findMinesInLine(givenLines[yPos], yPos));
        }
        return minePositions;
    }

    private static ArrayList<Position> findMinesInLine(String givenLine, int yPos) {
        ArrayList<Position> minePositions = new ArrayList<Position>();
        int xPos = -1;
        boolean found = false;
        do {
            xPos = givenLine.indexOf('*', xPos + 1);
            found = (xPos >= 0);
            if (found) {
                Position mine = new Position(xPos, yPos);
                minePositions.add(mine);
            }
        } while (found);
        return minePositions;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    ArrayList<Position> getMinePositions() {
        return new ArrayList<Position>(minePositions);
    }

}