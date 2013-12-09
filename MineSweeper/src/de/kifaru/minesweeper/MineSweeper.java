package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class MineSweeper {

    static List<String> makeCheatSheetForBoard(String[] givenBoard) {
        final Board board = Board.parse(givenBoard);
        final List<Position> minePositions = board.getMinePositions();
        final ArrayList<Position> impactRegions = MineSweeper.getImpactRegions(minePositions);
        final CheatSheet sheet = new CheatSheet(board.getWidth(), board.getHeight());
        sheet.addImpact(impactRegions);
        sheet.putMines(minePositions);
        List<String> result = sheet.toStrings();
        return result;
    }

    private static ArrayList<Position> getImpactRegions(final List<Position> minePositions) {
        final ArrayList<Position> impactRegion = new ArrayList<Position>();
        for (Position mine:minePositions) {
            impactRegion.addAll(MineSweeper.getImpactRegion(mine));
        }
        return impactRegion;
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