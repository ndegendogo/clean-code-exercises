package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class CheatSheet {

    static List<String> formatBoard(Board board, int width, int height) {
        final List<String> result = new ArrayList<String>();
        for (int j = 0; j < height; j++) {
            result.add(formatLine(board, j, width));
        }
        return result;
    }

    private static String formatLine(Board board, int j, int width) {
        final StringBuilder line = new StringBuilder(width);
        for (int i = 0; i < width; i ++) {
            final Field field = board.get(new Position(i, j));
            line.append(formatField(field));
        }
        return line.toString();
    }

    private static String formatField(final Field field) {
        if (field == null) {
            return "0";
        } else if (field instanceof MineField) {
            return "*";
        } else if (field instanceof ImpactField) {
            return format((ImpactField)field);
        } else {
            return "";
        }
    }

    private static String format(final ImpactField field) {
        return Integer.toString(field.getImpact());
    }
}