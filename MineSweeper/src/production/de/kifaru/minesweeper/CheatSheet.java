package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class CheatSheet {

    final Board board;
    
    private CheatSheet(final Board board) {
        this.board = board;
    }
    
    static List<String> formatBoard(final Board board) {
        final CheatSheet cheatSheet = new CheatSheet(board);
        return cheatSheet.formatAllLines();
    }

    private List<String> formatAllLines() {
        final List<String> lines = new ArrayList<String>();
        for (int lineNumber = 0; lineNumber < board.getHeight(); lineNumber++) {
            lines.add(formatLine(lineNumber));
        }
        return lines;
    }

    private String formatLine(final int lineNumber) {
        final StringBuilder line = new StringBuilder(board.getWidth());
        for (int colNumber = 0; colNumber < board.getWidth(); colNumber ++) {
            line.append(formatField(lineNumber, colNumber));
        }
        return line.toString();
    }

    private char formatField(final int lineNumber, final int colNumber) {
        final Field field = board.getField(colNumber, lineNumber);
        return field.format();
    }

}