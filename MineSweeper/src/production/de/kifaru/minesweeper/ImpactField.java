package de.kifaru.minesweeper;

class ImpactField extends Field {
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

    char format() throws MineSweeperException {
        return CheatSheet.formatImpactField(impact);
    }
}
