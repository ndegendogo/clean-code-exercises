package de.kifaru.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

public class ImpactMapTest {

    private static final Position givenPosition = new Position(1, 2);
    
    @Test
    public void addImpactToEmptyField() {
        final ImpactMap map = makeEmptyMap();
        whenAddImpact(map);
        thenImpact(map, 1);
    }

    @Test
    public void addImpactToImpactField_add() {
        final ImpactMap map = makeMapWithImpactField();
        whenAddImpact(map);
        thenImpact(map, 2);
    }
    
    private ImpactMap makeEmptyMap() {
        return new ImpactMap();
    }
    
    private ImpactMap makeMapWithImpactField() {
        final ImpactMap map = new ImpactMap();
        map.put(givenPosition, new ImpactField(1));
        return map;
    }

    private void whenAddImpact(final ImpactMap map) {
        map.addImpact(givenPosition);
    }

    private void thenImpact(final ImpactMap map, final int impact) {
        final ImpactField field = map.get(givenPosition);
        assertEquals(impact, field.getImpact());
    }

}
