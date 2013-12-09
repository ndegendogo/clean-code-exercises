package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class CheatSheet {
    class Field {
        private boolean isMine;
        private int impact;
        
        public String toString() {
            if (isMine) {
                return "*";
            } else {
                return new Integer(impact).toString();
            }
        }

        private void setMine() {
            isMine = true;
        }

        private void addImpact() {
            impact += 1;
        }
    }

    private final int width;
    private final int height;
    private Field[][] fields;

    CheatSheet(int width, int height) {
        this.width = width;
        this.height = height;
        fields = new Field[height][width];
        for(int j = 0; j < height; j ++) {
            for (int i = 0; i < width; i ++) {
                fields[j][i] = new Field();
            }
        }
    }
    
    void addImpact(List<Position> impactRegion) {
        for(Position pos:impactRegion) {
            if (pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < height) {
                fields[pos.y][pos.x].addImpact();
            }
        }
    }

    void patchMines(List<Position> minePositions) {
        for (Position pos:minePositions) {
            fields[pos.y][pos.x].setMine();
        }
    }

    List<String> toStrings() {
        List<String> result = new ArrayList<String>();
        for (int j = 0; j < height; j++) {
            StringBuilder line = new StringBuilder(width);
            for (int i = 0; i < width; i ++) {
                line.append(fields[j][i].toString());
            }
            result.add(line.toString());
        }
        return result;
    }
    
}