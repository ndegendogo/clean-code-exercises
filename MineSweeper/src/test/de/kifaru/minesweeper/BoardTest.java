package de.kifaru.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

    private static final Position GIVEN_POSITION = new Position(1, 2);
    
    @Test
    public void addImpactToEmptyField() {
        final BoardImpl board = createEmptyBoard();
        whenAddImpact(board);
        thenImpact(board, 1);
    }

    @Test
    public void addImpactToImpactField_add() {
        final BoardImpl board = createBoardWithImpactField();
        whenAddImpact(board);
        thenImpact(board, 2);
    }
    
    @Test
    public void putMine_onEmptyField() {
        final BoardImpl board = createEmptyBoard();
        whenPutMine(board);
        thenMine(board);
    }

    @Test
    public void putMine_replaceImpactField() {
        final BoardImpl board = createBoardWithImpactField();
        whenPutMine(board);
        thenMine(board);
    }

    @Test
    public void putMine_onMineField() {
        final BoardImpl board = createBoardWithMineField();
        whenPutMine(board);
        thenMine(board);
    }

    private static BoardImpl createEmptyBoard() {
        final Configuration config = new ConfigurationMock(3, 4);
        return new BoardImpl(config);
    }
    
    private static BoardImpl createBoardWithImpactField() {
        final BoardImpl board = createEmptyBoard();
        board.addImpact(GIVEN_POSITION);
        return board;
    }

    private static BoardImpl createBoardWithMineField() {
        final BoardImpl board = createEmptyBoard();
        board.putMine(GIVEN_POSITION);
        return board;
    }

    private static void whenAddImpact(final BoardImpl board) {
        board.addImpact(GIVEN_POSITION);
    }

    private static void whenPutMine(final BoardImpl board) {
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
