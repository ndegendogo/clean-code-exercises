package de.kifaru.minesweeper;

interface Board {

    public int getWidth();

    public int getHeight();

    public Field getField(final int colNumber, final int lineNumber);

}