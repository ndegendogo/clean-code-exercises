package de.kifaru.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

public class ImpactMapTest {

    static private Position givenPosition = new Position(1, 2);
    
    @Test
    public void addImpactToEmptyField() {
        ImpactMap map = makeEmptyMap();
        whenAddImpact(map);
        thenImpact(map, 1);
    }

    @Test
    public void addImpactToImpactField_add() {
        ImpactMap map = makeMapWithImpactField();
        whenAddImpact(map);
        thenImpact(map, 2);
    }
    
    private ImpactMap makeEmptyMap() {
        return new ImpactMap();
    }
    
    private ImpactMap makeMapWithImpactField() {
        ImpactMap map = new ImpactMap();
        map.put(givenPosition, new ImpactField());
        return map;
    }

    private void whenAddImpact(ImpactMap map) {
        map.addImpact(givenPosition);
    }

    private void thenImpact(ImpactMap map, final int impact) {
        Field field = map.get(givenPosition);
        assertTrue(field instanceof ImpactField);
        assertEquals(impact, ((ImpactField)field).getImpact());
    }

}
