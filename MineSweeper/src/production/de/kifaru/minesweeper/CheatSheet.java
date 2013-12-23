package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class CheatSheet {

    static List<String> formatBoard(final Board board) {
        final int width = board.getWidth();
        final int height = board.getHeight();
        final List<String> lines = new ArrayList<String>();
        for (int lineNumber = 0; lineNumber < height; lineNumber++) {
            lines.add(formatLine(board, lineNumber, width));
        }
        return lines;
    }

    private static String formatLine(final Board board, final int lineNumber, final int width) {
        final StringBuilder line = new StringBuilder(width);
        for (int colNumber = 0; colNumber < width; colNumber ++) {
            final Field field = board.getField(colNumber, lineNumber);
            line.append(formatField(field));
        }
        return line.toString();
    }

    static char formatField(final Field field) {
        return field.format();
    }

    static char formatMineField() {
        return '*';
    }

    static char formatImpactField(final int impact) {
        ensureImpactValid(impact);
        return Integer.toString(impact).charAt(0);
    }

    private static void ensureImpactValid(final int impact) throws MineSweeperException {
        if (impact < 0) {
            throw new MineSweeperException(MineSweeperException.ErrorCode.IMPACT_UNDERFLOW);
        } else if (impact > 9) {
            throw new MineSweeperException(MineSweeperException.ErrorCode.IMPACT_OVERFLOW);
        }
    }
}