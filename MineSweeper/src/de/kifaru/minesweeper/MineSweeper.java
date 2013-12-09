package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class MineSweeper {

    static List<String> makeCheatSheetForBoard(String[] givenBoard) {
        final Configuration config = Configuration.parse(givenBoard);
        final List<Position> minePositions = config.getMinePositions();
        final List<Position> impactRegions = getImpactRegions(minePositions);
        final Board board = new Board();
        board.putMines(minePositions);
        board.addImpact(impactRegions);
        List<String> result = CheatSheet.formatBoard(board, config.getWidth(), config.getHeight());
        return result;
    }

    private static List<Position> getImpactRegions(final List<Position> minePositions) {
        final List<Position> impactRegions = new ArrayList<Position>();
        for (Position mine:minePositions) {
            impactRegions.addAll(getImpactRegion(mine));
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