package de.kifaru.minesweeper;

import java.util.HashMap;

class BoardMock implements Board {

    private final int width;
    private final int height;
    private HashMap<Position, Field> fields;

    BoardMock(final int width, final int height) {
        this.width = width;
        this.height = height;
        fields = new HashMap<Position, Field>();
    }
    
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Field getField(final int colNumber, final int lineNumber) {
        return fields.get(new Position(colNumber, lineNumber));
    }

}
