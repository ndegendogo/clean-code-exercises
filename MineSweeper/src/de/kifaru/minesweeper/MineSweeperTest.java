package de.kifaru.minesweeper;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.junit.*;

public class MineSweeperTest {
    
    Vector<Position> mines;
    private Board board;

    @Test
    public void emptyBoard_OneFieldOnly() {
        String[] givenBoard = new String[] {"."};
        
        board = parse(givenBoard);

        String[] cheatSheet = new String[board.getHeight()];
        StringBuilder line = new StringBuilder();
        line.append(0);
        cheatSheet[0] = line.toString();
        
        String[] expectedSheet = new String[]{"0"};
        assertTrue(Arrays.equals(expectedSheet, cheatSheet));
    }

    @Test
    public void emptyBoard_OneLineOnly() {
        String[] givenBoard = new String[] {"..."};
        
        board = parse(givenBoard);

        String[] cheatSheet = new String[board.getHeight()];
        final int width = board.getWidth();
        
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < width; i++) {
            line.append(0);
        }
        cheatSheet[0] = line.toString();
        
        String[] expectedSheet = new String[]{"000"};
        assertTrue(Arrays.equals(expectedSheet, cheatSheet));
    }

    @Test
    public void emptyBoard_MultipleLines() {
        String[] givenBoard = new String[] 
                {".....",
                 ".....",
                 "....."
                };
        
        board = parse(givenBoard);
        
        final int height = board.getHeight();
        final int width = board.getWidth();
        String[] cheatSheet = new String[height];
        
        for (int j=0; j < height; j++) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < width; i++) {
                line.append(0);
            }
            cheatSheet[j] = line.toString();
        }
        
        String[] expectedSheet = new String[]{"00000", "00000", "00000"};
        assertTrue(Arrays.equals(expectedSheet, cheatSheet));
    }

    @Test
    public void oneLine_oneMine() {
        String[] givenBoard = new String[] {".*."};
        
        board = parse(givenBoard);
        
        final int height = board.getHeight();
        final int width = board.getWidth();
        StringBuilder[] fieldOfDamage = new StringBuilder[height];
        
        for (int j=0; j < height; j++) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < width; i++) {
                line.append(1);
            }
            fieldOfDamage[j] = line;
        }
        
        fieldOfDamage[0].setCharAt(1, '*');
        
        String[] cheatSheet = new String[height];
        for (int j = 0; j < height; j++) {
            cheatSheet[j] = fieldOfDamage[j].toString();
        }
        
        String[] expectedSheet = new String[]{"1*1"};
        assertTrue(Arrays.equals(expectedSheet, cheatSheet));
    }

    @Test
    public void multipleLines_oneMine() {
        String[] givenBoard = new String[] {"...", ".*.", "..."};
        
        board = parse(givenBoard);
        
        final int height = board.getHeight();
        final int width = board.getWidth();
        StringBuilder[] fieldOfDamage = new StringBuilder[height];
        
        for (int j=0; j < height; j++) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < width; i++) {
                line.append(1);
            }
            fieldOfDamage[j] = line;
        }
        
        fieldOfDamage[1].setCharAt(1, '*');
        
        String[] cheatSheet = new String[height];
        for (int j = 0; j < height; j++) {
            cheatSheet[j] = fieldOfDamage[j].toString();
        }
        
        String[] expectedSheet = new String[]{"111", "1*1", "111"};
        assertTrue(Arrays.equals(expectedSheet, cheatSheet));
    }

    
    @Test
    public void multipleLines_multipleMines() {
        String[] givenBoard = new String[] {"......", 
                                            ".*..*.", 
                                            "......"};
        
        board = parse(givenBoard);
        
        final int height = board.getHeight();
        final int width = board.getWidth();
        StringBuilder[] fieldOfDamage = new StringBuilder[height];
        
        for (int j=0; j < height; j++) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < width; i++) {
                line.append(1);
            }
            fieldOfDamage[j] = line;
        }
                
        for (Position mine:mines) {
            fieldOfDamage[mine.y].setCharAt(mine.x, '*');
        }
        
        String[] cheatSheet = new String[height];
        for (int j = 0; j < height; j++) {
            cheatSheet[j] = fieldOfDamage[j].toString();
        }
        
        String[] expectedSheet = new String[]{"111111", "1*11*1", "111111"};
        assertTrue(Arrays.equals(expectedSheet, cheatSheet));
    }
    
    
    
    
    @Test
    public void findOneMineInOneLine() {
        String givenLine = ".*.";
        
        int xPos = givenLine.indexOf('*');
        int yPos = 0;
        Position mine = new Position(xPos, yPos);
            
        Position expected = new Position(1, 0);
        assertTrue(mine.equals(expected));
    }
    
    @Test
    public void findOneMineAsListInOneLine() {
        String givenLine = ".*.";
        
        mines = new Vector<Position>();
        int yPos = 0;
        int xPos = givenLine.indexOf('*');
        Position mine = new Position(xPos, yPos);
        mines.add(mine);
            
        Position[] expectedArray = new Position[] {new Position(1,0)};
        List<Position> expectedList = Arrays.asList(expectedArray);
        assertEquals(mines, expectedList);
    }
    
    @Test
    public void findMultipleMinesInOneLine() {
        String givenLine = ".*..*.";
        
        int yPos = 0;
        mines = findMinesInLine(givenLine, yPos);
            
        Position[] expectedArray = new Position[] {new Position(1,0), new Position(4,0)};
        List<Position> expectedList = Arrays.asList(expectedArray);
        assertEquals(mines, expectedList);
    }

    @Test
    public void findMultipleMinesInMultipleLines() {
        String[] givenLines = {"......", 
                               ".*..*.", 
                               "......",
                               "......",
                               ".*..*.", 
                               "......",
                              };
        
        findAllMinePositions(givenLines);
            
        Position[] expectedArray = new Position[] {new Position(1,1), new Position(4,1), new Position(1,4), new Position(4,4)};
        List<Position> expectedList = Arrays.asList(expectedArray);
        assertEquals(mines, expectedList);
    }

    
    
    
    Board parse(String[] givenBoard) {
        Board board = new Board();
        board.height = givenBoard.length;
        board.width = givenBoard[0].length();
        findAllMinePositions(givenBoard);
        return board;
    }
    
    Vector<Position> findAllMinePositions(String[] givenLines) {
        mines = new Vector<Position>();
        for(int yPos = 0; yPos < givenLines.length; yPos ++) {
            mines.addAll(findMinesInLine(givenLines[yPos], yPos));
        }
        return mines;
    }
    
    
    Vector<Position> findMinesInLine(String givenLine, int yPos) {
        Vector<Position> mines = new Vector<Position>();
        int xPos = -1;
        while(true) {
            xPos = givenLine.indexOf('*', xPos + 1);
            if (xPos < 0) {
                break;
            }
            Position mine = new Position(xPos, yPos);
            mines.add(mine);
        }
        return mines;
    }

    class Board {
        int width;
        int height;
        
        int getWidth() {
            return width;
        }

        int getHeight() {
            return height;
        }
        
    }
    
}
