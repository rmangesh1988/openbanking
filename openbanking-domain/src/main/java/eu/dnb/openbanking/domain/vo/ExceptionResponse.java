package eu.dnb.openbanking.domain.vo;

import java.util.List;

/**
 * Created by rmang on 15-03-2018.
 */
public class ExceptionResponse {

    private String errorNumber;

    private String errorMessage;

    private String errorDocumentation;

    private List<ErrorDetail> errorDetails;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String errorNumber, String errorMessage, String errorDocumentation, List<ErrorDetail> errorDetails) {
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
        this.errorDocumentation = errorDocumentation;
        this.errorDetails = errorDetails;
    }

    public void setErrorNumber(String errorNumber) {
        this.errorNumber = errorNumber;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorDocumentation(String errorDocumentation) {
        this.errorDocumentation = errorDocumentation;
    }

    public void setErrorDetails(List<ErrorDetail> errorDetails) {
        this.errorDetails = errorDetails;
    }

    public String getErrorNumber() {
        return errorNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorDocumentation() {
        return errorDocumentation;
    }

    public List<ErrorDetail> getErrorDetails() {
        return errorDetails;
    }
}
