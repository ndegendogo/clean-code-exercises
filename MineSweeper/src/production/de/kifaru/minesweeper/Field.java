package de.kifaru.minesweeper;

class Field {
    
    private FieldContent content;
    private int impact;
    
    Field() {
        content = FieldContent.EMPTY;
    }
    
    Field(int impact) {
        this.content = FieldContent.IMPACT;
        this.impact = impact;
    }

    Field(FieldContent contentEnum) {
        this.content = contentEnum;
        if (contentEnum == FieldContent.IMPACT) {
            impact = 1;
        }
    }

    boolean isEmpty() {
        return content == FieldContent.EMPTY;
    }

    boolean isMine() {
        return content == FieldContent.MINE;
    }

    boolean isImpactField() {
        return content == FieldContent.IMPACT;
    }

    int getImpact() {
        return impact;
    }
    
    void addImpact(int increment) {
        if (isEmpty()) {
            impact = 1;
            content = FieldContent.IMPACT;
        } else if (isImpactField()) {
            impact += increment;
        }
    }

    void putMine() {
        content = FieldContent.MINE;
    }

    char format() {
        switch (content) {
            case EMPTY:
                return CheatSheet.formatEmptyField();
            case MINE:
                return CheatSheet.formatMineField();
            case IMPACT:
                return CheatSheet.formatImpactField(getImpact());
            default:
                return 0;
        }
    }

    enum FieldContent {
        EMPTY,
        MINE,
        IMPACT,
    ;
    }
}
