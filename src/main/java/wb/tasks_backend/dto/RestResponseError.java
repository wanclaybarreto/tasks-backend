package wb.tasks_backend.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class RestResponseError {

    private String error;


    private RestResponseError() {}

    public String getError() {
        return error;
    }

    public static RestResponseError fromValidationError(Errors errors) {
        RestResponseError rre = new RestResponseError();
        StringBuilder sb = new StringBuilder();

        for (ObjectError error : errors.getAllErrors()) {
            sb.append(error.getDefaultMessage()).append(". ");
        }

        rre.error = sb.deleteCharAt(sb.length() - 1).toString();
        return rre;
    }

    public static RestResponseError fromMessage(String message) {
        RestResponseError rre = new RestResponseError();
        rre.error = message + ".";
        return rre;
    }

}
