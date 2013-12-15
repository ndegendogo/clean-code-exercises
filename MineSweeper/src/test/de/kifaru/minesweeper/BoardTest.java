package de.kifaru.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

    private static final Position GIVEN_POSITION = new Position(1, 2);
    
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
        board.setField(GIVEN_POSITION.getX(), GIVEN_POSITION.getY(), new ImpactField(1));
        return board;
    }

    private Board makeBoardWithMineField() {
        final Board board = new Board(3, 4);
        board.setField(GIVEN_POSITION.getX(), GIVEN_POSITION.getY(), new MineField());
        return board;
    }

    private void whenAddImpact(final Board board) {
        board.addImpact(GIVEN_POSITION);
    }

    private void whenPutMine(final Board board) {
        board.putMine(GIVEN_POSITION);
    }
    
    private void thenImpact(final Board board, final int impact) {
        final Field field =  board.getField(GIVEN_POSITION.getX(), GIVEN_POSITION.getY());
        
        assertTrue(field instanceof ImpactField);
        assertEquals(impact, ((ImpactField)field).getImpact());
    }

    private void thenMine(final Board board) {
        final Field field = board.getField(GIVEN_POSITION.getX(), GIVEN_POSITION.getY());
        assertTrue(field instanceof MineField);
    }
}
