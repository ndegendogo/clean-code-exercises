package de.kifaru.minesweeper;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ConfigurationTest {

    @Test
    public void findOneMineInOneLine() {
        String[] givenLines = {".*."};
        
        List<Position> mines = Configuration.findAllMineFields(givenLines);
            
        Position[] expectedArray = new Position[] {new Position(1,0)};
        assertEquals(mines, Arrays.asList(expectedArray));
    }
    
    @Test
    public void findMultipleMinesInOneLine() {
        String[] givenLines = {".*..*."};
        
        List<Position> mines = Configuration.findAllMineFields(givenLines);
            
        Position[] expectedArray = new Position[] {new Position(1,0), new Position(4,0)};
        assertEquals(mines, Arrays.asList(expectedArray));
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
        
        List<Position> mines = Configuration.findAllMineFields(givenLines);
            
        Position[] expectedArray = new Position[] {new Position(1,1), new Position(4,1), new Position(1,4), new Position(4,4)};
        assertEquals(mines, Arrays.asList(expectedArray));
    }
    
    @Test
    public void parseEmptyBoard_OneFieldOnly() {
        String[] givenBoard = new String[] {"."};
        
        Configuration board = Configuration.parse(givenBoard);

        assertEquals(1, board.getHeight());
        assertEquals(1, board.getWidth());
        assertEquals(Arrays.asList(new Position[] {}), board.getMineFields());
    }

    @Test
    public void parseEmptyBoard_OneLineOnly() {
        String[] givenBoard = new String[] {"..."};
        
        Configuration board = Configuration.parse(givenBoard);

        assertEquals(1, board.getHeight());
        assertEquals(3, board.getWidth());
        assertEquals(Arrays.asList(new Position[] {}), board.getMineFields());
    }

    @Test
    public void parseEmptyBoard_MultipleLines() {
        String[] givenBoard = new String[] 
                {".....",
                 ".....",
                 "....."
                };
        
        Configuration board = Configuration.parse(givenBoard);
        
        assertEquals(3, board.getHeight());
        assertEquals(5, board.getWidth());
        assertEquals(Arrays.asList(new Position[] {}), board.getMineFields());
    }

    @Test
    public void parseOneLine_oneMine() {
        String[] givenBoard = new String[] {".*."};
        
        Configuration board = Configuration.parse(givenBoard);
        
        final Position[] expectedPositions = new Position[] {new Position(1, 0)};
        assertEquals(1, board.getHeight());
        assertEquals(3, board.getWidth());
        assertEquals(Arrays.asList(expectedPositions), board.getMineFields());
    }

    @Test
    public void parseMultipleLines_oneMine() {
        String[] givenBoard = new String[] {"...", ".*.", "..."};
        
        Configuration board = Configuration.parse(givenBoard);
        
        final Position[] expectedPositions = new Position[] {new Position(1, 1)};
        assertEquals(3, board.getHeight());
        assertEquals(3, board.getWidth());
        assertEquals(Arrays.asList(expectedPositions), board.getMineFields());
    }

    
    @Test
    public void parseMultipleLines_multipleMines() {
        String[] givenBoard = new String[] {"......", 
                                            ".*..*.", 
                                            "......"};
        
        Configuration board = Configuration.parse(givenBoard);
        
        final Position[] expectedPositions = new Position[] 
            {new Position(1, 1),
             new Position(4, 1)
            };
        assertEquals(3, board.getHeight());
        assertEquals(6, board.getWidth());
        assertEquals(Arrays.asList(expectedPositions), board.getMineFields());
    }
    
    
}