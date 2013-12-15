package de.kifaru.minesweeper;

class MineField extends Field {

    MineField() {
    }

    char format() {
        return CheatSheet.formatMineField();
    }
}
