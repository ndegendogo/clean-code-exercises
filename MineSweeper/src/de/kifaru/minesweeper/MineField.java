package de.kifaru.minesweeper;

public class MineField extends Field {

    MineField() {
    }

    String format() {
        return CheatSheet.formatMineField();
    }
}
