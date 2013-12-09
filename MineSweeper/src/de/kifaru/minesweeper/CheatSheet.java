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

        private void addImpact(int impact) {
            this.impact += impact;
        }
    }

    private final int width;
    private final int height;
    private CheatSheetField[][] fields;

    private CheatSheet(int width, int height) {
        this.width = width;
        this.height = height;
        fields = new CheatSheetField[height][width];
        for(int j = 0; j < height; j ++) {
            for (int i = 0; i < width; i ++) {
                fields[j][i] = new CheatSheetField();
            }
        }
    }
    
    private CheatSheet(int width, int height, List<ImpactField> impactRegion, List<MineField> minePositions) {
        this(width, height);
        addImpact(impactRegion);
        putMines(minePositions);
    }
    
    private void addImpact(List<ImpactField> impactRegion) {
        for(ImpactField pos:impactRegion) {
            final int x = pos.x;
            final int y = pos.y;
            final int impact = pos.impact;
            if (isInRange(x, y)) {
                fields[y][x].addImpact(impact);
            }
        }
    }

    private boolean isInRange(final int x, final int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    private void putMines(List<MineField> minePositions) {
        for (Field pos:minePositions) {
            fields[pos.y][pos.x].setMine();
        }
    }

    static List<String> format(int width, int height, List<ImpactField> impactRegions, List<MineField> minePositions) {
        final CheatSheet sheet = new CheatSheet(width, height, impactRegions, minePositions);
        final CheatSheetField[][] fields = sheet.fields;
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