package de.kifaru.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

public class CheatSheetTest {

    @Test
    public void formatMineField() {
        final Field field = new MineField();
        final char formattedField = CheatSheet.formatField(field);
        assertEquals('*', formattedField);
    }
    
    @Test
    public void formatImpactField() {
        final Field field = new ImpactField(7);
        final char formattedField = CheatSheet.formatField(field);
        assertEquals('7', formattedField);
    }
    
    @Test
    public void overflowImpactField() {
        final Field field = new ImpactField(10);
        try {
            CheatSheet.formatField(field);
            fail();
        } catch(MineSweeperException e) {
            assertEquals("Impact overflow", e.getMessage());
        }
    }
    
    @Test
    public void underflowImpactField() {
        final Field field = new ImpactField(-1);
        try {
            CheatSheet.formatField(field);
            fail();
        } catch(MineSweeperException e) {
            assertEquals("Impact underflow", e.getMessage());
        }
    }
    
}
