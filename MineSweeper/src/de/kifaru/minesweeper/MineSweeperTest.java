package de.kifaru.minesweeper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

public class MineSweeperTest {
    
    @Test
    public void emptyBoard_OneFieldOnly() {
        String[] givenBoard = new String[] {"."};
        
        Board board = Board.parse(givenBoard);

        final int height = board.getHeight();
        final int width = board.getWidth();
        
        Integer[]impact = new Integer[width];
        Arrays.fill(impact, 0);
        
        StringBuilder line = makeLine(impact);
        
        List<String> cheatSheet = new ArrayList<String>();
        cheatSheet.add(line.toString());
        
        List<String> expectedSheet = Arrays.asList(new String[]{"0"});
        assertEquals(expectedSheet, cheatSheet);
    }

    @Test
    public void emptyBoard_OneLineOnly() {
        String[] givenBoard = new String[] {"..."};
        
        final Board board = Board.parse(givenBoard);

        final int height = board.getHeight();
        final int width = board.getWidth();
        
        Integer[]impact = new Integer[width];
        Arrays.fill(impact, 0);
        
        StringBuilder line = makeLine(impact);
        List<String> cheatSheet = new ArrayList<String>();
        cheatSheet.add(line.toString());
        
        List<String> expectedSheet = Arrays.asList(new String[]{"000"});
        assertEquals(expectedSheet, cheatSheet);
    }

    @Test
    public void emptyBoard_MultipleLines() {
        String[] givenBoard = new String[] 
                {".....",
                 ".....",
                 "....."
                };
        
        final Board board = Board.parse(givenBoard);
        
        final int height = board.getHeight();
        final int width = board.getWidth();

        Integer[][]impact = new Integer[height][width];
        for (int j=0; j < height; j++) {
            Arrays.fill(impact[j], 0);
        }

        List<StringBuilder> lines = new ArrayList<StringBuilder>();
        for (int j=0; j < height; j++) {
            StringBuilder line = makeLine(impact[j]);
            lines.add(line);
        }
        
        List<String> cheatSheet = new ArrayList<String>();
        for (int j=0; j < height; j++) {
            cheatSheet.add(lines.get(j).toString());
        }

        List<String> expectedSheet = Arrays.asList(new String[]{"00000", "00000", "00000"});
        assertEquals(expectedSheet, cheatSheet);
    }

    @Test
    public void oneLine_oneMine() {
        String[] givenBoard = new String[] {".*."};
        
        final Board board = Board.parse(givenBoard);
        
        final int height = board.getHeight();
        final int width = board.getWidth();

        Integer[][]impact = new Integer[height][width];
        for (int j=0; j < height; j++) {
            Arrays.fill(impact[j], 1);
        }

        List<StringBuilder> lines = new ArrayList<StringBuilder>();
        for (int j=0; j < height; j++) {
            StringBuilder line = makeLine(impact[j]);
            lines.add(line);
        }
        
        lines.get(0).setCharAt(1, '*');

        List<String> cheatSheet = new ArrayList<String>();
        for (int j=0; j < height; j++) {
            cheatSheet.add(lines.get(j).toString());
        }
        
        List<String> expectedSheet = Arrays.asList(new String[]{"1*1"});
        assertEquals(expectedSheet, cheatSheet);
    }

    @Test
    public void multipleLines_oneMine() {
        String[] givenBoard = new String[] {"...", ".*.", "..."};
        
        final Board board = Board.parse(givenBoard);
        final int height = board.getHeight();
        final int width = board.getWidth();

        Integer[][]impact = new Integer[height][width];
        for (int j=0; j < height; j++) {
            Arrays.fill(impact[j], 1);
        }

        List<StringBuilder> lines = new ArrayList<StringBuilder>();
        for (int j=0; j < height; j++) {
            StringBuilder line = makeLine(impact[j]);
            lines.add(line);
        }
        
        lines.get(1).setCharAt(1, '*');
        
        List<String> cheatSheet = new ArrayList<String>();
        for (int j=0; j < height; j++) {
            cheatSheet.add(lines.get(j).toString());
        }
        
        List<String> expectedSheet = Arrays.asList(new String[]{"111", "1*1", "111"});
        assertEquals(expectedSheet, cheatSheet);
    }

    
    @Test
    public void multipleLines_multipleMines() {
        String[] givenBoard = new String[] {"......", 
                                            ".*..*.", 
                                            "......"};
        
        final Board board = Board.parse(givenBoard);
        final int height = board.getHeight();
        final int width = board.getWidth();

        Integer[][]impact = new Integer[height][width];
        for (int j=0; j < height; j++) {
            Arrays.fill(impact[j], 1);
        }

        List<StringBuilder> lines = new ArrayList<StringBuilder>();
        for (int j=0; j < height; j++) {
            StringBuilder line = makeLine(impact[j]);
            lines.add(line);
        }
        
        ArrayList<Position> minePositions = board.getMinePositions();
        for(Position pos:minePositions){
            lines.get(pos.y).setCharAt(pos.x, '*');
        }
        
        List<String> cheatSheet = new ArrayList<String>();
        for (int j=0; j < height; j++) {
            cheatSheet.add(lines.get(j).toString());
        }
        
        List<String> expectedSheet = Arrays.asList(new String[]{"111111", "1*11*1", "111111"});
        assertEquals(expectedSheet, cheatSheet);
    }
    
    //@Test
    public void oneMine_limitedImpact() {
        String[] givenBoard = new String[] {".....", 
                                            ".....",
                                            "..*..", 
                                            ".....",
                                            ".....",
                                            };
        
        final Board board = Board.parse(givenBoard);
        final int height = board.getHeight();
        final int width = board.getWidth();
        final List<Position> minePositions = board.getMinePositions();
        
        
        StringBuilder[] fieldOfDamage = new StringBuilder[height];
        
        for (int j=0; j < height; j++) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < width; i++) {
                line.append(1);
            }
            fieldOfDamage[j] = line;
        }
                
        for (Position mine:minePositions) {
            fieldOfDamage[mine.y].setCharAt(mine.x, '*');
        }
        
        String[] cheatSheet = new String[height];
        for (int j = 0; j < height; j++) {
            cheatSheet[j] = fieldOfDamage[j].toString();
        }
        
        String[] expectedSheet = new String[]{"00000", "01110", "01*10", "01110", "00000"};
        assertTrue(Arrays.equals(expectedSheet, cheatSheet));
    }
    
    private StringBuilder makeLine(Integer[] impact) {
        final int length = impact.length;
        StringBuilder line = new StringBuilder(length);
        line.setLength(length);
        for (int i = 0; i < length; i ++) {
            String s = impact[i].toString();
            line.setCharAt(i, s.charAt(0));
        }
        return line;
    }

}
