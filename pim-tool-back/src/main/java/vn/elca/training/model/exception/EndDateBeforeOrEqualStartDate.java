package vn.elca.training.model.exception;

import java.time.LocalDate;

public class EndDateBeforeOrEqualStartDate extends RuntimeException {
    private final LocalDate endDate;
    private final LocalDate startDate;

    public EndDateBeforeOrEqualStartDate(LocalDate startDate, LocalDate endDate) {
        super("The end date must be after the start date.");
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
