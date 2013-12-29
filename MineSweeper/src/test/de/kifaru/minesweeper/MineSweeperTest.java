package de.kifaru.minesweeper;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.*;

public class MineSweeperTest {
    
    @Test
    public void emptyBoard_OneFieldOnly() {
        final String[] givenConfig = new String[] {"."};
        final List<String> cheatSheet = makeCheatSheet(givenConfig);
        verifyCheatSheet(cheatSheet, new String[]{"0"});
    }

    @Test
    public void emptyBoard_OneLineOnly() {
        final String[] givenConfig = new String[] {"..."};
        final List<String> cheatSheet = makeCheatSheet(givenConfig);
        verifyCheatSheet(cheatSheet, new String[]{"000"});
    }

    @Test
    public void emptyBoard_MultipleLines() {
        final String[] givenConfig = new String[]{".....",
                     ".....",
                     "....."
                    };
        final List<String> cheatSheet = makeCheatSheet(givenConfig);
        verifyCheatSheet(cheatSheet, new String[]{"00000", 
                                                  "00000", 
                                                  "00000"});
    }

    @Test
    public void oneLine_oneMine() {
        final String[] givenConfig = new String[] {".*."};
        final List<String> cheatSheet = makeCheatSheet(givenConfig);
        verifyCheatSheet(cheatSheet, new String[]{"1*1"});
    }

    @Test
    public void oneLine_multipleMines() {
        final String[] givenConfig = new String[] {".*..*."};
        final List<String> cheatSheet = makeCheatSheet(givenConfig);
        verifyCheatSheet(cheatSheet, new String[]{"1*11*1"});
    }

    @Test
    public void multipleLines_oneMine() {
        final String[] givenConfig = new String[]{"...", 
                     ".*.", 
                     "..."};
        final List<String> cheatSheet = makeCheatSheet(givenConfig);
        verifyCheatSheet(cheatSheet, new String[]{"111", 
                                                  "1*1", 
                                                  "111"});
    }
    
    @Test
    public void multipleLines_multipleMines() {
        final String[] givenConfig = new String[]{"......", 
                     ".*..*.", 
                     "......"};
        final List<String> cheatSheet = makeCheatSheet(givenConfig);
        verifyCheatSheet(cheatSheet, new String[]{"111111", 
                                                  "1*11*1", 
                                                  "111111"});
    }
    
    @Test
    public void oneMine_limitedImpact() {
        final String[] givenConfig = new String[] {".....", 
                      ".....",
                      "..*..", 
                      ".....",
                      ".....",
                     };
        final List<String> cheatSheet = makeCheatSheet(givenConfig);
        verifyCheatSheet(cheatSheet, new String[]{"00000", 
                                                  "01110", 
                                                  "01*10", 
                                                  "01110", 
                                                  "00000"});
    }
    
    @Test
    public void twoMines_overlappingImpact() {
        final String[] givenConfig = new String[] {".....", 
                      ".*...",
                      ".....", 
                      "...*.",
                      ".....",
                     };
        final List<String> cheatSheet = makeCheatSheet(givenConfig);
        verifyCheatSheet(cheatSheet, new String[]{"11100", 
                                                  "1*100", 
                                                  "11211", 
                                                  "001*1", 
                                                  "00111"});
    }

    @Test
    public void minesAtEdgesAndInCorners() {
        final String[] givenConfig = new String[] {"..*..", 
                      ".....",
                      "*...*", 
                      ".....",
                      "*...*",
                     };
        final List<String> cheatSheet = makeCheatSheet(givenConfig);
        verifyCheatSheet(cheatSheet, new String[]{"01*10", 
                                                  "12121", 
                                                  "*101*", 
                                                  "22022", 
                                                  "*101*"});
    }

    @Test
    public void minesWithMaximumImpact() {
        final String[] givenConfig = new String[] {".....", 
                      ".***.",
                      ".*.*.", 
                      ".***.",
                      ".....",
                     };
        final List<String> cheatSheet = makeCheatSheet(givenConfig);
        verifyCheatSheet(cheatSheet, new String[]{"12321", 
                                                  "2***2", 
                                                  "3*8*3", 
                                                  "2***2", 
                                                  "12321"});
    }

    @Test
    public void compactBlockOfMines() {
        final String[] givenConfig = new String[] {".....", 
                      ".***.",
                      ".***.", 
                      ".***.",
                      ".....",
                     };
        final List<String> cheatSheet = makeCheatSheet(givenConfig);
        verifyCheatSheet(cheatSheet, new String[]{"12321", 
                                                  "2***2", 
                                                  "3***3", 
                                                  "2***2", 
                                                  "12321"});
    }

    @Test
    public void emptyBoard_ZeroWidth_illegal() {
        try {
            final String[] givenConfig = new String[] {""};
            makeCheatSheet(givenConfig);
            fail();
        } catch (MineSweeperException e) {
            assertEquals("Illegal configuration", e.getMessage());
        }
    }

    @Test
    public void emptyBoard_ZeroHeight_illegal() {
        try {
            final String[] givenConfig = new String[] {};
            makeCheatSheet(givenConfig);
            fail();
        } catch (MineSweeperException e) {
            assertEquals("Illegal configuration", e.getMessage());
        }
    }

    private List<String> makeCheatSheet(final String[] givenConfig) {
        return MineSweeper.makeCheatSheet(Arrays.asList(givenConfig));
    }

    private static void verifyCheatSheet(final List<String> cheatSheet, final String[] strings) {
        final List<String> expectedCheatSheet = Arrays.asList(strings);
        assertEquals(expectedCheatSheet, cheatSheet);
    }
    
}
