package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MineSweeper {

    static List<String> makeCheatSheetForBoard(String[] givenBoard) {
        final Board board = Board.parse(givenBoard);
        final int width = board.getWidth();
        final int height = board.getHeight();
        final List<MineField> minePositions = board.getMineFields();
        final List<ImpactField> impactRegions = MineSweeper.getImpactRegions(minePositions);
        Collections.sort(impactRegions);
        List<String> result = CheatSheet.format(width, height, impactRegions, minePositions);
        return result;
    }

    private static List<ImpactField> getImpactRegions(final List<MineField> minePositions) {
        final List<ImpactField> impactRegions = new ArrayList<ImpactField>();
        for (MineField mine:minePositions) {
            impactRegions.addAll(MineSweeper.getImpactRegion(mine));
        }
        return impactRegions;
    }

    private static List<ImpactField> getImpactRegion(MineField mine) {
        List<ImpactField> impactRegion = new ArrayList<ImpactField>();
        for (int x = mine.x - 1; x <= mine.x + 1; x ++) {
            for (int y = mine.y - 1; y <= mine.y + 1; y ++) {
                impactRegion.add(new ImpactField(x, y));
            }
        }
        return impactRegion;
    }
    
}