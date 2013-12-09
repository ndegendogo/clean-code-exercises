package de.kifaru.minesweeper;

import java.util.HashMap;
import java.util.List;

public class Board extends HashMap<Position, Field> {

    private static final long serialVersionUID = 1L;
    private static final MineField theMine = new MineField();
    
    public Board() {
    }
    
    public Board (ImpactMap map) {
        this.putAll(map);
    }
    
    void putMine(Position pos) {
        put(pos, theMine);
    }
    
    void putMines(List<Position> positions) {
        for(Position pos:positions) {
            putMine(pos);
        }
    }
}
