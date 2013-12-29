package de.kifaru.minesweeper;

import static de.kifaru.minesweeper.MineSweeperException.ErrorCode;

class Field {
    
    private static int MIN_IMPACT = 0;
    private static int MAX_IMPACT = 9;

    private FieldContent content;
    
    Field() {
        content = FieldContent.EMPTY;
    }
    
    Field(FieldContent content) {
        this.content = content;
    }

    Field(final int impact) {
        content = createContent(impact);
    }

    private FieldContent createContent(final int impact) {
        ensureImpactValid(impact);
        return FieldContent.values()[impact];
    }

    private static void ensureImpactValid(final int impact) {
        if (impact < MIN_IMPACT) {
            throw new MineSweeperException(ErrorCode.IMPACT_UNDERFLOW);
        } else if (impact > MAX_IMPACT) {
            throw new MineSweeperException(ErrorCode.IMPACT_OVERFLOW);
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
    
    void addImpact(final int increment) {
        if (!isMineField()) {
            content = createContent(getImpact() + increment);
        }
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
