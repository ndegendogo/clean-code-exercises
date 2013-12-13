package de.kifaru.minesweeper;

import java.util.ArrayList;
import java.util.List;

// Review: Why is this class there?
// Not asking about method parse, that makes sense, but the class as such...
// Couldn't this be merged into Board?
class Configuration {
    private final int width;
    private final int height;
    private final List<Position> minePositions;
    
    private Configuration(final int width, final int height, final List<Position> minePositions) {
        this.width = width;
        this.height = height;
        this.minePositions = minePositions;
    }
    
    static Configuration parse(final String[] givenConfig) {
        final int height = givenConfig.length;
        // Review: width should be final.
        // The code could be simplified to:
        // final int width = givenConfig.length > 0 ? givenConfig[0].length() : 0;
        int width;
        if (givenConfig.length > 0) {
            width = givenConfig[0].length();
        } else {
            width = 0;
        }
        final List<Position> minePositions = findAllMinePositions(givenConfig);
        return new Configuration(width, height, minePositions);
    }
    
    static List<Position> findAllMinePositions(final String[] givenLines) {
        final List<Position> mines = new ArrayList<Position>();
        for(int yPos = 0; yPos < givenLines.length; yPos ++) {
            mines.addAll(findMinesInLine(givenLines[yPos], yPos));
        }
        return mines;
    }

    // Review: Variable name yPos too long. y is sufficient and perfectly clear.
    // Review: Variable name givenLine confusing. Why givenLine? Why not just line?
    private static List<Position> findMinesInLine(final String givenLine, final int yPos) {
        final List<Position> mines = new ArrayList<Position>();
        int xPos = -1;
        // Review: Initializing to false is redundant.
        // Review: unnecessary do-while. Try this code:
        // for (int xPos = -1; (xPos = givenLine.indexOf('*', xPos + 1)) > 0; ) {
        //     mines.add(new Position(xPos, yPos);
        // }
        // Review: Maybe it's personal, but I find indexOf a bit difficult to understand.
        // I would've done this:
        // for (int x = 0; x < givenLine.length(); x++) {
        //     if (givenLine.charAt(x) == '*') {
        //         mines.add(new Position(x, yPos);
        //     }
        // }
        boolean found = false;
        do {
            xPos = givenLine.indexOf('*', xPos + 1);
            found = (xPos >= 0);
            if (found) {
                mines.add(new Position(xPos, yPos));
            }
        } while (found);
        return mines;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    List<Position> getMinePositions() {
        return new ArrayList<Position>(minePositions);
    }

}