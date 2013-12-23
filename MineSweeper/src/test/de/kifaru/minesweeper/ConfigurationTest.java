package de.kifaru.minesweeper;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ConfigurationTest {

    @Test
    public void findOneMineInOneLine() {
        final String[] givenLines = {".*."};
        final List<Position> foundMines = ConfigurationImpl.findAllMinePositions(givenLines);
        final Position[] expectedMines = new Position[] {new Position(1,0)};
        verifyMines(expectedMines, foundMines);
    }

    @Test
    public void findMultipleMinesInOneLine() {
        final String[] givenLines = {".*..*."};
        final List<Position> foundMines = ConfigurationImpl.findAllMinePositions(givenLines);
        final Position[] expectedMines = new Position[] {new Position(1,0), new Position(4,0)};
        verifyMines(expectedMines, foundMines);
    }

    @Test
    public void findMultipleMinesInMultipleLines() {
        final String[] givenLines = 
               {"......", 
                ".*..*.", 
                "......",
                "......",
                ".*..*.", 
                "......",
               };
        final List<Position> foundMines = ConfigurationImpl.findAllMinePositions(givenLines);
        final Position[] expectedMines = new Position[] {new Position(1,1), new Position(4,1), new Position(1,4), new Position(4,4)};
        verifyMines(expectedMines, foundMines);
    }
    
    @Test
    public void parseEmptyConfig_OneFieldOnly() {
        final String[] givenConfig = new String[] {"."};
        final Configuration config = ConfigurationImpl.parse(givenConfig);
        verifyConfig(config, 1, 1, new Position[] {});
    }

    @Test
    public void parseEmptyConfig_OneLineOnly() {
        final String[] givenConfig = new String[] {"..."};
        final Configuration config = ConfigurationImpl.parse(givenConfig);
        verifyConfig(config, 3, 1, new Position[] {});
    }

    @Test
    public void parseEmptyConfig_MultipleLines() {
        final String[] givenConfig = new String[] 
                {".....",
                 ".....",
                 "....."
                };
        final Configuration config = ConfigurationImpl.parse(givenConfig);
        verifyConfig(config, 5, 3, new Position[] {});
    }

    @Test
    public void parseOneLine_oneMine() {
        final String[] givenConfig = new String[] {".*."};
        final Configuration config = ConfigurationImpl.parse(givenConfig);
        final Position[] expectedPositions = new Position[] {new Position(1, 0)};
        verifyConfig(config, 3, 1, expectedPositions);
    }

    @Test
    public void parseMultipleLines_oneMine() {
        final String[] givenConfig = new String[] {"...", ".*.", "..."};
        final Configuration config = ConfigurationImpl.parse(givenConfig);
        final Position[] expectedPositions = new Position[] {new Position(1, 1)};
        verifyConfig(config, 3, 3, expectedPositions);
    }

    
    @Test
    public void parseMultipleLines_multipleMines() {
        final String[] givenConfig = new String[] {
                "......", 
                ".*..*.", 
                "......"};
        final Configuration config = ConfigurationImpl.parse(givenConfig);
        final Position[] expectedPositions = new Position[] 
            {new Position(1, 1),
             new Position(4, 1)
            };
        verifyConfig(config, 6, 3, expectedPositions);
    }

    private void verifyMines(final Position[] expectedPositions, final List<Position> minePositions) {
        assertEquals(Arrays.asList(expectedPositions), minePositions);
    }
    
    private void verifyConfig(final Configuration config, final int expectedWidth, final int expectedHeight,
            final Position[] expectedPositions) {
        assertEquals(expectedHeight, config.getHeight());
        assertEquals(expectedWidth, config.getWidth());
        assertEquals(Arrays.asList(expectedPositions), config.getMinePositions());
    }
    
}