package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

// Review: As the name suggests, the CheatSheet is an instance, an object.
// So, why is formatBoard returning List<String> instead of CheatSheet?
// CheatSheet could have instances, formatBoard could be the constructor.
class CheatSheet {

    // Review: Why do width and height get passed separately?
    // Shouldn't the Board know its width and height?
    static List<String> formatBoard(final Board board, final int width, final int height) {
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

    private static String formatField(final Field field) {
        return field.format();
    }

    static String formatMineField() {
        return "*";
    }

    static String formatImpactField(int impact) {
        return Integer.toString(impact);
    }
}