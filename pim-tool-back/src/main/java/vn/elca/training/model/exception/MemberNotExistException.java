package vn.elca.training.model.exception;

public class MemberNotExistException extends RuntimeException {
    private final String visa;

    public MemberNotExistException(String visas) {
        super("The following visas do not exist: " + visas);
        this.visa = visas;
    }

    public String getVisa() {
        return visa;
    }
}
