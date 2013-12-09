package de.kifaru.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

    static private Position givenPosition = new Position(1, 2);
    
    @Test
    public void putMine_onEmptyField() {
        Board board = makeEmptyBoard();
        whenPutMine(board);
        thenMine(board);
    }

    @Test
    public void putMine_replaceImpactField() {
        Board board = makeBoardWithImpactField();
        whenPutMine(board);
        thenMine(board);
    }

    @Test
    public void putMine_onMineField() {
        Board board = makeBoardWithMineField();
        whenPutMine(board);
        thenMine(board);
    }

    @Test
    public void addImpactToEmptyField() {
        Board board = makeEmptyBoard();
        whenAddImpact(board);
        thenImpact(board, 1);
    }

    @Test
    public void addImpactToMineField_noEffect() {
        Board board = makeBoardWithMineField();
        whenAddImpact(board);
        thenMine(board);
    }

    @Test
    public void addImpactToImpactField_add() {
        Board board = makeBoardWithImpactField();
        whenAddImpact(board);
        thenImpact(board, 2);
    }
    
    private Board makeEmptyBoard() {
        return new Board();
    }
    
    private Board makeBoardWithMineField() {
        Board board = new Board();
        board.put(givenPosition, new MineField());
        return board;
    }

    private Board makeBoardWithImpactField() {
        Board board = new Board();
        board.put(givenPosition, new ImpactField());
        return board;
    }

    private void whenPutMine(Board board) {
        board.putMine(givenPosition);
    }

    private void whenAddImpact(Board board) {
        board.addImpact(givenPosition);
    }

    private void thenMine(Board board) {
        Field field = board.get(givenPosition);
        assertTrue(field instanceof MineField);
    }
    
    private void thenImpact(Board board, final int impact) {
        Field field = board.get(givenPosition);
        assertTrue(field instanceof ImpactField);
        assertEquals(impact, ((ImpactField)field).getImpact());
    }

}
