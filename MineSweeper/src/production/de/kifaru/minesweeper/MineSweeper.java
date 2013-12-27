package de.kifaru.minesweeper;

import java.util.List;

public class MineSweeper {

    static List<String> makeCheatSheetForConfig(final String[] givenConfig) {
        final Configuration config = ConfigurationImpl.parse(givenConfig);
        final Board board = BoardImpl.createBoard(config);
        return CheatSheet.formatBoard(board);
    }
    
}