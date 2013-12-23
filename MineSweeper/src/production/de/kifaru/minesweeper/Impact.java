package de.kifaru.minesweeper;

class Impact extends FieldContent {
    private int impact;

    Impact() {
        this.impact = 1;
    }

    Impact(int impact) {
        this.impact = impact;
    }
    
    int getImpact() {
        return impact;
    }

    void addImpact(int increment) {
        impact += increment;
    }

    char format() {
        return CheatSheet.formatImpactField(impact);
    }
}
