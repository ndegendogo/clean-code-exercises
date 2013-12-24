package de.kifaru.minesweeper;

class Field {
    
    private FieldContent content;
    
    Field() {
        content = FieldContent.EMPTY;
    }
    
    Field(FieldContent content) {
        this.content = content;
    }

    Field(int impact) {
        ensureImpactValid(impact);
        content = FieldContent.values()[impact];
    }

    private static void ensureImpactValid(final int impact) {
        if (impact < 0) {
            throw new MineSweeperException(MineSweeperException.ErrorCode.IMPACT_UNDERFLOW);
        } else if (impact > 9) {
            throw new MineSweeperException(MineSweeperException.ErrorCode.IMPACT_OVERFLOW);
        }
    }

    boolean isEmptyField() {
        return content.isEmpty();
    }

    boolean isMineField() {
        return content.isMine();
    }

    boolean isImpactField() {
        return content.isImpact();
    }

    int getImpact() {
        return content.getImpact();
    }
    
    void addImpact(int increment) {
        if (isMineField()) {
            return;
        }
        int impact = getImpact() + increment;
        ensureImpactValid(impact);
        content = FieldContent.values()[impact];
    }

    void putMine() {
        content = FieldContent.MINE;
    }

    char format() {
        return content.toChar();
    }

    enum FieldContent {
        EMPTY ('0'),
        IMPACT ('1'),
        IMPACT2 ('2'),
        IMPACT3 ('3'),
        IMPACT4 ('4'),
        IMPACT5 ('5'),
        IMPACT6 ('6'),
        IMPACT7 ('7'),
        IMPACT8 ('8'),
        IMPACT9 ('9'),
        MINE ('*'),
    ;
        
        private final char asChar;
        
        private FieldContent(char asChar) {
            this.asChar = asChar;
        }
        
        private boolean isEmpty() {
            return this == EMPTY;
        }

        private boolean isMine() {
            return this == MINE;
        }

        private boolean isImpact() {
            return !isEmpty() && !isMine();
        }

        private int getImpact() {
            if (isMine()) {
                return 0;
            } else {
                return ordinal();
            }
        }

        private char toChar() {
            return asChar;
        }
    }
}
