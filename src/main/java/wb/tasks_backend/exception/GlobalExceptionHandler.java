package wb.tasks_backend.exception;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wb.tasks_backend.dto.RestResponseError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RepositoryConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponseError handleRepConstraintViolationException(RepositoryConstraintViolationException e) {
        return RestResponseError.fromValidationError(e.getErrors());
    }

    @ExceptionHandler(DuplicatedTaskException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponseError handleDuplicatedTaskException(DuplicatedTaskException e) {
        return RestResponseError.fromMessage(e.getMessage());
    }

}
