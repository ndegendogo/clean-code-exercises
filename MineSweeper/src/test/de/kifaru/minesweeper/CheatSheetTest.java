package de.kifaru.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

public class CheatSheetTest {

    @Test
    public void formatEmptyField() {
        final Field field = new Field();
        final char formattedField = CheatSheet.formatField(field);
        assertEquals('0', formattedField);
    }
    
    @Test
    public void formatMineField() {
        final Field field = new Field(new Mine());
        final char formattedField = CheatSheet.formatField(field);
        assertEquals('*', formattedField);
    }
    
    @Test
    public void formatImpactField() {
        final Field field = new Field(new Impact(7));
        final char formattedField = CheatSheet.formatField(field);
        assertEquals('7', formattedField);
    }
    
    @Test
    public void overflowImpactField() {
        final Field field = new Field(new Impact(10));
        try {
            CheatSheet.formatField(field);
            fail();
        } catch(MineSweeperException e) {
            assertEquals("Impact overflow", e.getMessage());
        }
    }
    
    @Test
    public void underflowImpactField() {
        final Field field = new Field(new Impact(-1));
        try {
            CheatSheet.formatField(field);
            fail();
        } catch(MineSweeperException e) {
            assertEquals("Impact underflow", e.getMessage());
        }
    }
    
}
