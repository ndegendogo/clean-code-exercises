package de.kifaru.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

    private static final Position GIVEN_POSITION = new Position(1, 2);
    
    @Test
    public void addImpactToEmptyField() {
        final Board board = createEmptyBoard();
        whenAddImpact(board);
        thenImpact(board, 1);
    }

    @Test
    public void addImpactToImpactField_add() {
        final Board board = createBoardWithImpactField();
        whenAddImpact(board);
        thenImpact(board, 2);
    }
    
    @Test
    public void putMine_onEmptyField() {
        final Board board = createEmptyBoard();
        whenPutMine(board);
        thenMine(board);
    }

    @Test
    public void putMine_replaceImpactField() {
        final Board board = createBoardWithImpactField();
        whenPutMine(board);
        thenMine(board);
    }

    @Test
    public void putMine_onMineField() {
        final Board board = createBoardWithMineField();
        whenPutMine(board);
        thenMine(board);
    }

    private static Board createEmptyBoard() {
        return new Board(3, 4);
    }
    
    private static Board createBoardWithImpactField() {
        final Board board = new Board(3, 4);
        board.setField(GIVEN_POSITION.getX(), GIVEN_POSITION.getY(), new ImpactField(1));
        return board;
    }

    private static Board createBoardWithMineField() {
        final Board board = new Board(3, 4);
        board.setField(GIVEN_POSITION.getX(), GIVEN_POSITION.getY(), new MineField());
        return board;
    }

    private static void whenAddImpact(final Board board) {
        board.addImpact(GIVEN_POSITION);
    }

    private static void whenPutMine(final Board board) {
        board.putMine(GIVEN_POSITION);
    }
    
    private static void thenImpact(final Board board, final int impact) {
        final Field field =  board.getField(GIVEN_POSITION.getX(), GIVEN_POSITION.getY());
        
        assertTrue(field instanceof ImpactField);
        assertEquals(impact, ((ImpactField)field).getImpact());
    }

    private static void thenMine(final Board board) {
        final Field field = board.getField(GIVEN_POSITION.getX(), GIVEN_POSITION.getY());
        assertTrue(field instanceof MineField);
    }
}
