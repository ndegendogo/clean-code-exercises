package de.kifaru.minesweeper;

public class ImpactField extends Field {
    private int impact;

    ImpactField() {
        impact = 1;
    }
    
    int getImpact() {
        return impact;
    }

    void addImpact(int increment) {
        impact += increment;
    }
}
