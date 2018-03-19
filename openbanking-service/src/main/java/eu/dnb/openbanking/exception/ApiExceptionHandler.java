package eu.dnb.openbanking.exception;

import eu.dnb.openbanking.domain.vo.ErrorDetail;
import eu.dnb.openbanking.domain.vo.ExceptionResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.slf4j.Logger;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;
import java.util.List;

/*
 * Created by rmang on 15-03-2018.
*/

@ControllerAdvice
public class ApiExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex) {
        logger.error("Exception occured : "+ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(DNBError.INTERNAL_SERVER_ERROR.getErrorCode(),
                DNBError.INTERNAL_SERVER_ERROR.getErrorMessage(),
                DNBError.INTERNAL_SERVER_ERROR.getUriString(), Arrays.asList(new ErrorDetail(DNBError.INTERNAL_SERVER_ERROR.getErrorCode(), null, "Something bad happened!")));
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        logger.error("EntityNotFoundException occured : "+ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(DNBError.MISSING_RESOURCE.getErrorCode(),
                DNBError.MISSING_RESOURCE.getErrorMessage(),
                DNBError.MISSING_RESOURCE.getUriString(), Arrays.asList(new ErrorDetail(DNBError.MISSING_RESOURCE.getErrorCode(), null, ex.getMessage())));
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityAlreadyExistsException ex) {
        logger.error("EntityAlreadyExistsException occured : "+ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(DNBError.REQUEST_DATA_ERROR.getErrorCode(),
                DNBError.REQUEST_DATA_ERROR.getErrorMessage(),
                DNBError.REQUEST_DATA_ERROR.getUriString(), Arrays.asList(new ErrorDetail(DNBError.REQUEST_DATA_ERROR.getErrorCode(), null, ex.getMessage())));
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error("MethodArgumentNotValidException occured : "+ex);
        List<ErrorDetail> errorDetails = ExceptionHelper.getBindingErrors(DNBError.REQUEST_DATA_ERROR.getErrorCode(), ex.getBindingResult());
        ExceptionResponse exceptionResponse = new ExceptionResponse(DNBError.REQUEST_DATA_ERROR.name(),
                DNBError.REQUEST_DATA_ERROR.getErrorMessage(),
                DNBError.REQUEST_DATA_ERROR.getUriString(), errorDetails);
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        logger.error("HttpRequestMethodNotSupportedException occured : "+ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(DNBError.METHOD_NOT_SUPPORTED.getErrorCode(),
                DNBError.METHOD_NOT_SUPPORTED.getErrorMessage(),
                DNBError.METHOD_NOT_SUPPORTED.getUriString(), Arrays.asList(new ErrorDetail(DNBError.METHOD_NOT_SUPPORTED.getErrorCode(), null, DNBError.METHOD_NOT_SUPPORTED.getErrorMessage())));
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);

    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        logger.error("NoHandlerFoundException occured : "+ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(DNBError.INCORRECT_URI.getErrorCode(),
                DNBError.INCORRECT_URI.getErrorMessage(),
                DNBError.INCORRECT_URI.getUriString(), Arrays.asList(new ErrorDetail(DNBError.INCORRECT_URI.getErrorCode(), null, DNBError.INCORRECT_URI.getErrorMessage())));
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        logger.error("HttpMediaTypeNotSupportedException occured : "+ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(DNBError.REQUEST_DATA_ERROR.getErrorCode(),
                DNBError.REQUEST_DATA_ERROR.getErrorMessage(),
                DNBError.REQUEST_DATA_ERROR.getUriString(), Arrays.asList(new ErrorDetail(DNBError.REQUEST_DATA_ERROR.getErrorCode(), null, DNBError.REQUEST_DATA_ERROR.getErrorMessage() + ". Unsupported Media Type")));
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);

    }

}
