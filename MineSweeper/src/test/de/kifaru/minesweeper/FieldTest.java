package de.kifaru.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

import de.kifaru.minesweeper.Field.FieldContent;

public class FieldTest {

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
        assertTrue(field.isMine());
    }
}
