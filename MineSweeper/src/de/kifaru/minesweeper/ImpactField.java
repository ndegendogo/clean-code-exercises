package de.kifaru.minesweeper;

public class ImpactField extends Field {
    private int impact;

    ImpactField(int impact) {
        this.impact = impact;
    }
    
    int getImpact() {
        return impact;
    }

    void addImpact(int increment) {
        impact += increment;
    }

    String format() {
        return CheatSheet.formatImpactField(impact);
    }
}
