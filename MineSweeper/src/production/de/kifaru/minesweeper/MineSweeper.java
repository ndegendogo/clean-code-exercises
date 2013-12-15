package de.kifaru.minesweeper;

import java.util.List;

public class MineSweeper {

    static List<String> makeCheatSheetForConfig(final String[] givenConfig) throws MineSweeperException {
        final Configuration config = Configuration.parse(givenConfig);
        final Board board = new Board(config.getWidth(), config.getHeight());
        board.putMines(config.getMinePositions());
        return CheatSheet.formatBoard(board);
    }
    
}