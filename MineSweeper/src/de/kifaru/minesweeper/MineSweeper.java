package de.kifaru.minesweeper;

import java.util.List;

class MineSweeper {

    static List<String> makeCheatSheetForConfig(final String[] givenConfig) {
        final Configuration config = Configuration.parse(givenConfig);
        final Iterable<Position> minePositions = config.getMinePositions();
        final Board board = new Board(config.getWidth(), config.getHeight());
        board.putMines(minePositions);
        return CheatSheet.formatBoard(board, board.getWidth(), board.getHeight());
    }
    
}