package vn.elca.training.model.exception;

public class DuplicateProjectNumberException extends RuntimeException {
    private final String message;

    public DuplicateProjectNumberException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
