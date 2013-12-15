package de.kifaru.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

    static private Position givenPosition = new Position(1, 2);
    
    @Test
    public void addImpactToEmptyField() {
        final Board board = makeEmptyBoard();
        whenAddImpact(board);
        thenImpact(board, 1);
    }

    @Test
    public void addImpactToImpactField_add() {
        final Board board = makeBoardWithImpactField();
        whenAddImpact(board);
        thenImpact(board, 2);
    }
    
    @Test
    public void putMine_onEmptyField() {
        final Board board = makeEmptyBoard();
        whenPutMine(board);
        thenMine(board);
    }

    @Test
    public void putMine_replaceImpactField() {
        final Board board = makeBoardWithImpactField();
        whenPutMine(board);
        thenMine(board);
    }

    @Test
    public void putMine_onMineField() {
        final Board board = makeBoardWithMineField();
        whenPutMine(board);
        thenMine(board);
    }

    private Board makeEmptyBoard() {
        return new Board(3, 4);
    }
    
    private Board makeBoardWithImpactField() {
        final Board board = new Board(3, 4);
        board.put(givenPosition, new ImpactField(1));
        return board;
    }

    private Board makeBoardWithMineField() {
        final Board board = new Board(3, 4);
        board.put(givenPosition, new MineField());
        return board;
    }

    private void whenAddImpact(final Board board) {
        board.addImpact(givenPosition);
    }

    private void whenPutMine(final Board board) {
        board.putMine(givenPosition);
    }
    
    private void thenImpact(final Board map, final int impact) {
        final ImpactField field = (ImpactField) map.get(givenPosition);
        assertEquals(impact, field.getImpact());
    }

    private void thenMine(final Board board) {
        final Field field = board.get(givenPosition);
        assertTrue(field instanceof MineField);
    }
}
