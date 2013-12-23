package de.kifaru.minesweeper;


interface Configuration {

    public int getWidth();

    public int getHeight();

    public Iterable<Position> getMinePositions();

}