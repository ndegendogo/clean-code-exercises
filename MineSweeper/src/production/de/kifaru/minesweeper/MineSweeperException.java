package de.kifaru.minesweeper;

class MineSweeperException extends Exception {

    private static final long serialVersionUID = 1L;

    public MineSweeperException() {
    }

    public MineSweeperException(ErrorCode reason) {
        super(reason.getErrorMessage());
    }
    
    enum ErrorCode {
        IMPACT_OVERFLOW (new String("Impact overflow")),
        IMPACT_UNDERFLOW (new String("Impact underflow"));
        
        private final String errorMessage;
        
        private ErrorCode(final String errorMessage) {
            this.errorMessage = errorMessage;
        }
        
        private String getErrorMessage() {
            return errorMessage;
        }
    }
}
