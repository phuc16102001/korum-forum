package vn.elca.training.web;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.elca.training.model.exception.DuplicateProjectNumberException;
import vn.elca.training.model.exception.EndDateBeforeOrEqualStartDate;
import vn.elca.training.model.exception.MemberNotExistException;
import vn.elca.training.model.exception.ProjectNotFoundException;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProjectNotFoundException.class)
    public String handleProjectNotFoundException(ProjectNotFoundException ex, HttpServletResponse response) {
        try {
            response.sendError(460, ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ex.getMessage();
    }

    @ExceptionHandler(DuplicateProjectNumberException.class)
    public String handleDuplicateProjectNumberException(DuplicateProjectNumberException ex, HttpServletResponse response) {
        try {
            response.sendError(461, ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ex.getMessage();
    }

    @ExceptionHandler(MemberNotExistException.class)
    public String handleMemberNotExistException(MemberNotExistException ex, HttpServletResponse response) {
        try {
            response.sendError(462, ex.getVisa().toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ex.getMessage();
    }

    @ExceptionHandler(EndDateBeforeOrEqualStartDate.class)
    public String handleEndDateBeforeOrEqualStartDateException(EndDateBeforeOrEqualStartDate ex, HttpServletResponse response) {
        try {
            response.sendError(463, ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ex.getMessage();
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public String handleOptimisticLockingFailureException(ObjectOptimisticLockingFailureException ex, HttpServletResponse response) {
        try {
            response.sendError(464, "Version violated.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ex.getMessage();
    }
}
