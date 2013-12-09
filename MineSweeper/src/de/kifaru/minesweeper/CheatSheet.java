package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

//import de.kifaru.minesweeper.CheatSheet.CheatSheetField;

class CheatSheet {
    static class CheatSheetField {
        boolean isMine;
        int impact;
        
        private void setMine() {
            isMine = true;
        }

        private void addImpact() {
            this.impact += 1;
        }
    }

    private final int width;
    private final int height;
    private CheatSheetField[][] fields;

    CheatSheet(int width, int height) {
        this.width = width;
        this.height = height;
        fields = new CheatSheetField[height][width];
        for(int j = 0; j < height; j ++) {
            for (int i = 0; i < width; i ++) {
                fields[j][i] = new CheatSheetField();
            }
        }
    }
    
    void addImpact(List<Position> impactRegion) {
        for(Position pos:impactRegion) {
            final int x = pos.x;
            final int y = pos.y;
            if (isInRange(x, y)) {
                fields[y][x].addImpact();
            }
        }
    }

    private boolean isInRange(final int x, final int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    void putMines(List<Position> minePositions) {
        for (Position pos:minePositions) {
            fields[pos.y][pos.x].setMine();
        }
    }

    List<String> format(int width, int height, List<Position> impactRegions, List<Position> minePositions) {
        List<String> result = new ArrayList<String>();
        for (int j = 0; j < height; j++) {
            StringBuilder line = new StringBuilder(width);
            for (int i = 0; i < width; i ++) {
                line.append(format(fields[j][i]));
            }
            result.add(line.toString());
        }
        return result;
    }
    
    private static String format(CheatSheetField field) {
        if (field.isMine) {
            return "*";
        } else {
            return new Integer(field.impact).toString();
        }
    }

}