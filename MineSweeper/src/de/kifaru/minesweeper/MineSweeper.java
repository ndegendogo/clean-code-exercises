package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

class MineSweeper {

    static List<String> makeCheatSheetForConfig(final String[] givenConfig) {
        final Configuration config = Configuration.parse(givenConfig);
        final List<Position> minePositions = config.getMinePositions();
        final List<Position> impactRegions = getImpactRegions(minePositions);
        final ImpactMap map = new ImpactMap();
        map.addImpact(impactRegions);
        final Board board = new Board(map);
        board.putMines(minePositions);
        return CheatSheet.formatBoard(board, config.getWidth(), config.getHeight());
    }

    private static List<Position> getImpactRegions(final List<Position> minePositions) {
        final List<Position> impactRegions = new ArrayList<Position>();
        for (Position mine:minePositions) {
            impactRegions.addAll(getImpactRegion(mine));
        }
        return impactRegions;
    }

    private static List<Position> getImpactRegion(final Position mine) {
        final List<Position> impactRegion = new ArrayList<Position>();
        for (int x = mine.x - 1; x <= mine.x + 1; x ++) {
            for (int y = mine.y - 1; y <= mine.y + 1; y ++) {
                impactRegion.add(new Position(x, y));
            }
        }
        return impactRegion;
    }
    
}