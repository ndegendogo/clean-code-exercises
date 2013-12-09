package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class MineSweeper {

    static List<String> makeCheatSheetForBoard(String[] givenBoard) {
        final Configuration board = Configuration.parse(givenBoard);
        final int width = board.getWidth();
        final int height = board.getHeight();
        final List<Position> minePositions = board.getMineFields();
        final List<Position> impactRegions = MineSweeper.getImpactRegions(minePositions);
        final CheatSheet sheet = new CheatSheet(width, height);
        sheet.addImpact(impactRegions);
        sheet.putMines(minePositions);
        List<String> result = sheet.format(width, height, impactRegions, minePositions);
        return result;
    }

    private static List<Position> getImpactRegions(final List<Position> minePositions) {
        final List<Position> impactRegions = new ArrayList<Position>();
        for (Position mine:minePositions) {
            impactRegions.addAll(MineSweeper.getImpactRegion(mine));
        }
        return impactRegions;
    }

    private static List<Position> getImpactRegion(Position mine) {
        List<Position> impactRegion = new ArrayList<Position>();
        for (int x = mine.x - 1; x <= mine.x + 1; x ++) {
            for (int y = mine.y - 1; y <= mine.y + 1; y ++) {
                impactRegion.add(new Position(x, y));
            }
        }
        return impactRegion;
    }
    
}