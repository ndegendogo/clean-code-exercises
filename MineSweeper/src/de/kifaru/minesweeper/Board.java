package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class Board {
    private int width;
    private int height;
    private List<MineField> mineFields;
    
    static Board parse(String[] givenBoard) {
        Board board = new Board();
        board.height = givenBoard.length;
        if (givenBoard.length > 0) {
            board.width = givenBoard[0].length();
        } else {
            board.width = 0;
        }
        board.mineFields = findAllMineFields(givenBoard);
        return board;
    }
    
    static List<MineField> findAllMineFields(String[] givenLines) {
        List<MineField> mineFields = new ArrayList<MineField>();
        for(int yPos = 0; yPos < givenLines.length; yPos ++) {
            mineFields.addAll(findMinesInLine(givenLines[yPos], yPos));
        }
        return mineFields;
    }

    private static List<MineField> findMinesInLine(String givenLine, int yPos) {
        List<MineField> mineFields = new ArrayList<MineField>();
        int xPos = -1;
        boolean found = false;
        do {
            xPos = givenLine.indexOf('*', xPos + 1);
            found = (xPos >= 0);
            if (found) {
                mineFields.add(new MineField(xPos, yPos));
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

    List<MineField> getMineFields() {
        return new ArrayList<MineField>(mineFields);
    }

}