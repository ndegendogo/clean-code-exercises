package de.kifaru.minesweeper;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.*;

public class MineSweeperTest {
    
    @Test
    public void emptyBoard_OneFieldOnly() {
        final List<String> cheatSheet = MineSweeper.makeCheatSheetForConfig(new String[] {"."});
        verifyCheatSheet(cheatSheet, new String[]{"0"});
    }

    @Test
    public void emptyBoard_OneLineOnly() {
        final List<String> cheatSheet = MineSweeper.makeCheatSheetForConfig(new String[] {"..."});
        verifyCheatSheet(cheatSheet, new String[]{"000"});
    }

    @Test
    public void emptyBoard_MultipleLines() {
        final List<String> cheatSheet = MineSweeper.makeCheatSheetForConfig(
                new String[]{".....",
                             ".....",
                             "....."
                            });
        verifyCheatSheet(cheatSheet, new String[]{"00000", 
                                                  "00000", 
                                                  "00000"});
    }

    @Test
    public void oneLine_oneMine() {
        final List<String> cheatSheet = MineSweeper.makeCheatSheetForConfig(new String[] {".*."});
        verifyCheatSheet(cheatSheet, new String[]{"1*1"});
    }

    @Test
    public void multipleLines_oneMine() {
        final List<String> cheatSheet = MineSweeper.makeCheatSheetForConfig(
                new String[]{"...", 
                             ".*.", 
                             "..."});
        verifyCheatSheet(cheatSheet, new String[]{"111", 
                                                  "1*1", 
                                                  "111"});
    }
    
    @Test
    public void multipleLines_multipleMines() {
        final List<String> cheatSheet = MineSweeper.makeCheatSheetForConfig(
                new String[]{"......", 
                             ".*..*.", 
                             "......"});
        verifyCheatSheet(cheatSheet, new String[]{"111111", 
                                                  "1*11*1", 
                                                  "111111"});
    }
    
    @Test
    public void oneMine_limitedImpact() {
        final List<String> cheatSheet = MineSweeper.makeCheatSheetForConfig(
                new String[] {".....", 
                              ".....",
                              "..*..", 
                              ".....",
                              ".....",
                             });
        verifyCheatSheet(cheatSheet, new String[]{"00000", 
                                                  "01110", 
                                                  "01*10", 
                                                  "01110", 
                                                  "00000"});
    }
    
    @Test
    public void twoMines_overlappingImpact() {
        final List<String> cheatSheet = MineSweeper.makeCheatSheetForConfig(
                new String[] {".....", 
                              ".*...",
                              ".....", 
                              "...*.",
                              ".....",
                             });
        verifyCheatSheet(cheatSheet, new String[]{"11100", 
                                                  "1*100", 
                                                  "11211", 
                                                  "001*1", 
                                                  "00111"});
    }

    @Test
    public void minesAtEdgesAndInCorners() {
        final List<String> cheatSheet = MineSweeper.makeCheatSheetForConfig(
                new String[] {"..*..", 
                              ".....",
                              "*...*", 
                              ".....",
                              "*...*",
                             });
        verifyCheatSheet(cheatSheet, new String[]{"01*10", 
                                                  "12121", 
                                                  "*101*", 
                                                  "22022", 
                                                  "*101*"});
    }

    @Test
    public void minesWithMaximumImpact() {
        final List<String> cheatSheet = MineSweeper.makeCheatSheetForConfig(
                new String[] {".....", 
                              ".***.",
                              ".*.*.", 
                              ".***.",
                              ".....",
                             });
        verifyCheatSheet(cheatSheet, new String[]{"12321", 
                                                  "2***2", 
                                                  "3*8*3", 
                                                  "2***2", 
                                                  "12321"});
    }

    @Test
    public void emptyBoard_ZeroWidth() {
        try {
            MineSweeper.makeCheatSheetForConfig(new String[] {""});
            fail();
        } catch (MineSweeperException e) {
            assertEquals("Illegal configuration", e.getMessage());
        }
    }

    @Test
    public void emptyBoard_ZeroHeight() {
        try {
            MineSweeper.makeCheatSheetForConfig(new String[] {});
            fail();
        } catch (MineSweeperException e) {
            assertEquals("Illegal configuration", e.getMessage());
        }
    }

    private static void verifyCheatSheet(final List<String> cheatSheet, final String[] strings) {
        final List<String> expectedCheatSheet = Arrays.asList(strings);
        assertEquals(expectedCheatSheet, cheatSheet);
    }
    
}
