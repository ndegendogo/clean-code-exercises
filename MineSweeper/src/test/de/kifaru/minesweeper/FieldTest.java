package de.kifaru.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

import de.kifaru.minesweeper.Field.FieldContent;

public class FieldTest {

    @Test
    public void validImpact_isImpactField() {
        final Field field = new Field(5);
        thenImpact(field, 5);
    }
    
    @Test
    public void zeroImpact_isEmptyField() {
        final Field field = new Field(0);
        thenEmpty(field);
    }

    @Test
    public void negativeImpact_isInvalidField() {
        try {
            new Field(-1);
            fail();
        } catch(MineSweeperException e) {
            assertEquals("Impact underflow", e.getMessage());
        }
    }

    @Test
    public void excessiveImpact_isInvalidField() {
        try {
            new Field(10);
            fail();
        } catch(MineSweeperException e) {
            assertEquals("Impact overflow", e.getMessage());
        }
    }

    @Test
    public void addImpactToEmptyField() {
        final Field field = createEmptyField();
        whenAddImpact(field);
        thenImpact(field, 1);
    }

    @Test
    public void addImpactToImpactField_add() {
        final Field field = createImpactField();
        whenAddImpact(field);
        thenImpact(field, 2);
    }
    
    @Test
    public void putMine_onEmptyField() {
        final Field field = createEmptyField();
        whenPutMine(field);
        thenMine(field);
    }

    @Test
    public void putMine_replaceImpactField() {
        final Field field = createImpactField();
        whenPutMine(field);
        thenMine(field);
    }

    @Test
    public void putMine_onMineField() {
        final Field field = createMineField();
        whenPutMine(field);
        thenMine(field);
    }

    @Test
    public void formatEmptyField() {
        final Field field = new Field();
        final char formattedField = field.format();
        assertEquals('0', formattedField);
    }
    
    @Test
    public void formatMineField() {
        final Field field = new Field(FieldContent.MINE);
        final char formattedField = field.format();
        assertEquals('*', formattedField);
    }
    
    @Test
    public void formatImpactField() {
        final Field field = new Field(7);
        final char formattedField = field.format();
        assertEquals('7', formattedField);
    }
    
    private static Field createEmptyField() {
        return new Field();
    }
    
    private static Field createImpactField() {
        return new Field(FieldContent.IMPACT);
    }

    private static Field createMineField() {
        return new Field(FieldContent.MINE);
    }

    private static void whenAddImpact(final Field field) {
        field.addImpact(1);
    }

    private static void whenPutMine(final Field field) {
        field.putMine();
    }
    
    private static void thenImpact(final Field field, final int impact) {        
        assertTrue(field.isImpactField());
        assertEquals(impact, field.getImpact());
    }

    private static void thenMine(final Field field) {
        assertTrue(field.isMineField());
    }

    private static void thenEmpty(final Field field) {
        assertTrue(field.isEmptyField());
    }
}
