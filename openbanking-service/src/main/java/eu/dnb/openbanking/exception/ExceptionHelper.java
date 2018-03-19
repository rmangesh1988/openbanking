package eu.dnb.openbanking.exception;

import eu.dnb.openbanking.domain.vo.ErrorDetail;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmang on 15-03-2018.
 */
public class ExceptionHelper {
    public static List<ErrorDetail> getBindingErrors(String dnbError, BindingResult bindingResult) {
        List<ErrorDetail> errorDetails = new ArrayList<>();
        ErrorDetail errorDetail = null;
        if(bindingResult.hasFieldErrors()) {
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                errorDetail = new ErrorDetail(dnbError, fieldError.getField(), fieldError.getDefaultMessage());
                errorDetails.add(errorDetail);
            }
        }
        return errorDetails;
    }
}
