package vn.elca.training.model.exception;

public class ProjectNotFoundException extends RuntimeException {
    private final Long id;

    public ProjectNotFoundException(Long id) {
        super(String.format("Project with id %d not found.", id));
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
