package de.kifaru.minesweeper;

class Field {
    static private final Field theEmptyField = new Field();
    FieldContent content;
    
    Field() {
        content = new Empty();
    }
    
    Field(FieldContent content) {
        this.content = content;
    }

    static Field getDefault() {
        return theEmptyField;
    }
    
    void addImpact(int increment) {
        if (isEmpty()) {
            content = new Impact();
        } else if (isImpactField()) {
            ((Impact) content).addImpact(increment);
        }
    }

    boolean isImpactField() {
        return content instanceof Impact;
    }

    boolean isEmpty() {
        return content instanceof Empty;
    }

    void putMine() {
        content = new Mine();
    }

    char format() {
        return content.format();
    }
}
