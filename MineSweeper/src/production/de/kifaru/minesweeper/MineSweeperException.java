package de.kifaru.minesweeper;

class MineSweeperException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final ErrorCode reason;
    
    public MineSweeperException(ErrorCode reason) {
        super(reason.getErrorMessage());
        this.reason = reason;
    }
    
    public MineSweeperException(ErrorCode reason, String additionalText) {
        super(reason.getErrorMessage() + additionalText);
        this.reason = reason;
    }
    
    ErrorCode getReason() {
        return reason;
    }

    enum ErrorCode {
        ILLEGAL_CONFIGURATION ("Illegal configuration"),
        IMPACT_OVERFLOW ("Impact overflow"),
        IMPACT_UNDERFLOW ("Impact underflow"),
        FILE_NOT_FOUND ("File not found: "),
        FILE_READ_ERROR("Error reading file: "),
        FILE_WRITE_ERROR("Error writing file: "),
        WRONG_USAGE("");
    ;
        
        private final String errorMessage;
        
        private ErrorCode(final String errorMessage) {
            this.errorMessage = errorMessage;
        }
        
        private String getErrorMessage() {
            return errorMessage;
        }

    }
}
